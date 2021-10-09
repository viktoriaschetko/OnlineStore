import com.fasterxml.jackson.databind.ObjectMapper;
import io.undertow.Undertow;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.BlockingHandler;
import io.undertow.util.Headers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HttpServer {

    public static void main(final String[] args) {
        initDb();
        startHttpServer();
    }

    private static void initDb() {
        DataBase.init(RandomStorePopulator.generate());

        startPurchasedProductsJanitor();
    }

    private static void startHttpServer() {
        Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(new BlockingHandler(exchange -> RequestHandler.resolve(exchange).handle(exchange)))
                .build()
                .start();
    }

    private static void startPurchasedProductsJanitor() {
        Executors
                .newScheduledThreadPool(1)
                .scheduleWithFixedDelay(new Janitor(), 2, 2, TimeUnit.MINUTES);
    }

    enum RequestHandler {
        GET_CATALOG {
            @Override
            public void handle(HttpServerExchange exchange) {
                if (Auth.isAuthenticated(exchange)) {
                    serializeCategories(exchange.getOutputStream(), DataBase.getCategories());
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                } else {
                    System.out.println("[HTTP-Log]: Not Authorized.");
                    exchange.setStatusCode(401); // Unauthorized
                }

                System.out.println("[HTTP-Log]: Handled GET_CATEGORIES request.");
            }

            private void serializeCategories(OutputStream out, List<Category> categories) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.writeValue(out, categories);
                } catch (IOException e) {
                    throw new RuntimeException("Unexpected error during catalog serialization.", e);
                }
            }
        },
        CREATE_ORDER {
            @Override
            public void handle(HttpServerExchange exchange) {
                if (Auth.isAuthenticated(exchange)) {
                    String productId = parseProductId(exchange);
                    String orderId = DataBase.createOrder(productId);
                    startOrderProcessing(orderId);

                    exchange.setStatusCode(200); // OK
                } else {
                    System.out.println("[HTTP-Log]: Not Authorized.");
                    exchange.setStatusCode(401); // Unauthorized
                }


                System.out.println("[HTTP-Log]: Handled CREATE_ORDER request.");
            }

            private void startOrderProcessing(String orderId) {
                new Thread(() -> {
                    try {
                        sleepFrom1To30Seconds();

                        DataBase.setOrderStatus(orderId, OrderStatus.PROCESSED);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }

            private String parseProductId(HttpServerExchange exchange) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    CreateOrderDto dto = objectMapper.readValue(exchange.getInputStream(), CreateOrderDto.class);
                    return dto.getProductId();
                } catch (IOException e) {
                    throw new RuntimeException("Unexpected error during catalog serialization.", e);
                }
            }

            private void sleepFrom1To30Seconds() throws InterruptedException {
                Thread.sleep((new Random().nextInt(30) + 1) * 1000);
            }
        },
        OPERATION_NOT_SUPPORTED {
            @Override
            public void handle(HttpServerExchange exchange) {
                exchange.setStatusCode(405); // Method not allowed

                System.out.println("[HTTP-Log]: Handled OPERATION_NOT_SUPPORTED request.");
            }
        };

        public abstract void handle(HttpServerExchange exchange);

        public static RequestHandler resolve(HttpServerExchange exchange) {
            if (getCatalogRequest(exchange)) {
                return GET_CATALOG;
            }
            if (createOrderRequest(exchange)) {
                return CREATE_ORDER;
            }
            return OPERATION_NOT_SUPPORTED;
        }

        private static boolean getCatalogRequest(HttpServerExchange e) {
            return e.getRequestMethod().toString().equals("GET")
                    && e.getRequestPath().equals("/catalog");
        }

        private static boolean createOrderRequest(HttpServerExchange e) {
            return e.getRequestMethod().toString().equals("POST")
                    && e.getRequestPath().equals("/orders");
        }
    }
}

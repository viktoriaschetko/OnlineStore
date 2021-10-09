import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DataBase {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:online_store;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public static void init(List<Category> categories) {
        initTables();
        populateData(categories);
    }

    public static List<Category> getCategories() {
        List<Category> categories = fetchCategories();

        categories.forEach(category -> {
            category.setProducts(fetchProductsForCategory(category.getId()));
        });

        return categories;
    }

    public static String createOrder(String productId) {
        String orderId = insertOrder(productId);
        System.out.println("[DB LOG]: New order with product '" + productId + "' was created.");
        return orderId;
    }

    public static void setOrderStatus(String orderId, OrderStatus status) {
        executeSql(
                String.format("UPDATE PURCHASE_ORDER SET status='%s' WHERE id='%s'", status.toString(), orderId)
        );

        System.out.println("[DB LOG]: Order status '" + status + "' is set for order '" + orderId + "'.");
    }

    public static void archiveStaleOrders() {
        List<Order> processedOrders = fetchProcessedOrders();
        processedOrders.forEach(order -> {
            setOrderStatus(order.getId(), OrderStatus.ARCHIVED);
        });

        System.out.println("[DB LOG]: stale orders were archived. Orders: " +
                processedOrders
                        .stream()
                        .map(Order::getId)
                        .collect(Collectors.toList()));
    }

    private static List<Category> fetchCategories() {
        List<Category> categories = new LinkedList<>();

        try (Connection connection = getDBConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CATEGORY");

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");

                categories.add(new Category(id, name));
            }

            stmt.close();
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch categories.", e);
        }

        return categories;
    }

    private static List<Product> fetchProductsForCategory(String categoryId) {
        List<Product> products = new LinkedList<>();

        try (Connection connection = getDBConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM PRODUCT WHERE category_id='%s'", categoryId));

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                int rate = rs.getInt("rate");
                double price = rs.getDouble("price");

                products.add(
                        Product.builder()
                                .id(id)
                                .name(name)
                                .rate(rate)
                                .price(price)
                                .build());
            }

            stmt.close();
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch products.", e);
        }

        return products;
    }

    private static List<Order> fetchProcessedOrders() {
        List<Order> orders = new LinkedList<>();

        try (Connection connection = getDBConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM PURCHASE_ORDER WHERE status='%s'",
                    OrderStatus.PROCESSED));

            while (rs.next()) {
                String id = rs.getString("id");
                String status = rs.getString("status");
                String productId = rs.getString("product_id");

                orders.add(new Order(id, OrderStatus.valueOf(status), productId));
            }

            stmt.close();
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch orders.", e);
        }

        return orders;
    }

    private static void populateData(List<Category> categories) {
        categories.forEach(category -> {
            insertCategory(category);
            category.getProducts().forEach(product -> {
                insertProduct(product, category);
            });
        });
    }

    private static void insertCategory(Category category) {
        executeSql(
                String.format("INSERT INTO CATEGORY(id, name) VALUES('%s', '%s')", category.getId(), category.getName())
        );
    }

    private static void insertProduct(Product product, Category category) {
        executeSql(
                String.format("INSERT INTO PRODUCT(id, name, rate, price, category_id) " +
                                "VALUES('%s', '%s', '%s', '%s', '%s')",
                        product.getId(), product.getName(), product.getRate(), product.getPrice(), category.getId())
        );
    }

    private static String insertOrder(String productId) {
        String orderId = UUID.randomUUID().toString();

        executeSql(
                String.format("INSERT INTO PURCHASE_ORDER(id, status, product_id) " +
                                "VALUES('%s', '%s', '%s')",
                        orderId, OrderStatus.NEW, productId)
        );

        return orderId;
    }

    private static void initTables() {
        // categories
        executeSql("CREATE TABLE CATEGORY(id varchar(36) primary key, name varchar(255))");

        // products
        executeSql("CREATE TABLE PRODUCT(" +
                "id varchar(36) primary key, " +
                "name varchar(255), " +
                "rate int, " +
                "price double, " +
                "category_id varchar (36), " +
                "foreign key (category_id) references CATEGORY(id))");

        // orders
        executeSql("CREATE TABLE PURCHASE_ORDER(" +
                "id varchar(36) primary key, " +
                "status varchar(100), " +
                "product_id varchar (36), " +
                "foreign key (product_id) references PRODUCT(id))");
    }

    private static void executeSql(String createTableDdl) {
        try (Connection connection = getDBConnection()) {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableDdl);
            stmt.close();
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute SQL query.", e);
        }
    }

    private static Connection getDBConnection() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to establish DB connection.", e);
        }
        try {
            return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish DB connection.", e);
        }
    }
}

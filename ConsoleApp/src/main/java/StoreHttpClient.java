import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class StoreHttpClient {

    private static final String SERVER_BASE_URL = "http://localhost:8080";

    public static List<Category> getCategories() {
        RequestSpecification request = RestAssured.given();
        setAuthHeader(request);
        Response response = request.get(SERVER_BASE_URL + "/catalog");

        return deserializeCategories(response.then().extract());
    }

    public static void createOrder(String productId) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        setAuthHeader(request);
        request.body(serializeCreateOrderDto(new CreateOrderDto(productId)));
        request.post(SERVER_BASE_URL + "/orders");
    }

    private static void setAuthHeader(RequestSpecification request) {
        request.header("Authorization", "Basic " +
                Base64.getEncoder().encodeToString("user:password".getBytes()));
    }

    @SuppressWarnings("unchecked")
    private static List<Category> deserializeCategories(ExtractableResponse<Response> response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readerForListOf(Category.class).readValue(response.body().asInputStream());
        } catch (IOException e) {
            throw new RuntimeException("Unexpected error during catalog serialization.", e);
        }
    }

    private static byte[] serializeCreateOrderDto(CreateOrderDto dto) {
        try {
            return new ObjectMapper().writeValueAsBytes(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unexpected error during CreateOrderDto object serialization.", e);
        }
    }
}

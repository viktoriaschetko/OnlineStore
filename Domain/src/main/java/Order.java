import java.util.UUID;

public class Order {

    private String id = UUID.randomUUID().toString();
    private OrderStatus status;
    private String productId;

    public Order() {}

    public Order(String id, OrderStatus status, String productId) {
        this.id = id;
        this.status = status;
        this.productId = productId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}

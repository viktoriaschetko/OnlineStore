public class CreateOrderDto {

    private String productId;

    public CreateOrderDto() {
    }

    public CreateOrderDto(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}

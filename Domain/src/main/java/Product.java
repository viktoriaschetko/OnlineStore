import java.util.UUID;

public class Product {

    private String id = UUID.randomUUID().toString();
    private String name;
    private int rate;
    private double price;

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ", " + "rate " + rate + ", " + "price " + price + "\n";
    }

    public static class ProductBuilder {

        private Product product = new Product();

        public ProductBuilder id(String id) {
            product.setId(id);
            return this;
        }

        public ProductBuilder name(String name) {
            product.setName(name);
            return this;
        }

        public ProductBuilder rate(int rate) {
            product.setRate(rate);
            return this;
        }

        public ProductBuilder price(double price) {
            product.setPrice(price);
            return this;
        }

        public Product build() {
            return product;
        }
    }

}

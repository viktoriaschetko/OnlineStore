import java.util.List;

public class Category {
    private String name;
    private List<Product> products;

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "name" + name + ", " + "products " + products;
    }
}
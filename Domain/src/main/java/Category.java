import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Category {

    private String id = UUID.randomUUID().toString();
    private String name;
    private List<Product> products = new ArrayList<>();

    public Category() {
    }

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return name + "\n " + products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    protected Object clone() {
        Category cloned = new Category(name);
        cloned.products = new ArrayList<>(products.size());
        products.forEach(cloned::addProduct);
        return cloned;
    }
}
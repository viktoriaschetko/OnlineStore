import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Product> products = new ArrayList<>();

   public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
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
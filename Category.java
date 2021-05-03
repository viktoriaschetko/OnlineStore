import java.util.ArrayList;
import java.util.List;

public class Category {
    private final List<Category> products = new ArrayList<Category>();
    public Category () {
        this.initStoreItems ();
    }

    public List<Category> getProducts() {
        return products;
    }

    private void initStoreItems() {
        String [] productNames = {};
        Integer []  productRate = new Integer[]{};
        Double [] productPrice = {};

        for (int i=0; i < productNames.length; i++) {
            this.products.add(new Product(i+1, productNames[i], productRate[i], productPrice[i]));
        }
    }
}

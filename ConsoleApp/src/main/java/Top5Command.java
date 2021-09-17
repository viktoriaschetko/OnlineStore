import java.util.ArrayList;
import java.util.List;


public class Top5Command {

    private final List<Category> categories;

    public Top5Command(List<Category> categories) {
        this.categories = categories;
    }

    public int execute() {
        List<Product> allProducts = new ArrayList<>();

        categories.forEach(c -> {
            allProducts.addAll(c.getProducts());
            allProducts.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        });

        System.out.println(allProducts.subList(0, 5));
        return 0;
    }
}






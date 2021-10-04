import java.util.ArrayList;
import java.util.List;


public class Top5Command {

    public int execute() {
        List<Category> categories = DataBase.getCategories();

        List<Product> allProducts = new ArrayList<>();

        categories.forEach(c -> {
            allProducts.addAll(c.getProducts());
            allProducts.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        });

        System.out.println(allProducts.subList(0, 5));
        return 0;
    }
}






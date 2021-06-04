import java.util.List;

public class StoreApp {

    public static void main(String[] args) {
        List<Category> categories = RandomStorePopulator.generate();
        System.out.println(categories);
    }
}
import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> cat = new ArrayList<>();
    private static Store instance;

    public List<Category> getCat() {
        return cat;
    }

    private Store() {
    }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }
}
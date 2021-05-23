import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private final List<Category> cat = new ArrayList<>();
    private final List<Product> prod = new ArrayList<>();

    public Store(Array Beer, Array Food, Array RockBand) {

    }

    public List<Category> getCat() {
        return cat;
    }

    public List<Product> getProd() {
        return prod;
    }}
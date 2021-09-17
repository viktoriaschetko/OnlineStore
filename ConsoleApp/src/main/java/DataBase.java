import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataBase {

    private static List<String> purchasedProducts = Collections.synchronizedList(new ArrayList<>());

    public static void addPurchasedProduct(String productName) {
        purchasedProducts.add(productName);
        System.out.println("[DB LOG]: '" + productName + "' was added to purchased products.");
    }

    public static void clearPurchasedProducts() {
        System.out.println("[DB LOG]: purchased products cleared. Products: " + purchasedProducts);
        purchasedProducts.clear();
    }
}

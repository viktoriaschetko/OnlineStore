import java.util.Collections;
import java.util.Comparator;

public class Product {

    private String name;
    private int rate;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ", " + "rate " + rate + ", " + "price " + price + "\n";
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

public class CreateOrderCommand {

    private final List<Category> categories;

    public CreateOrderCommand(List<Category> categories) {
        this.categories = categories;
    }

    public int execute() throws IOException {
        printProducts(categories);

        try {
            String productName = expectCustomerChoice();
            submitOrderForProcessing(productName);

            System.out.println("Your order of '" + productName + "' is accepted. Thank you!");
        } catch (OrderCancelledException e) {
            System.out.println("Sorry you didn't find anything. Hope to have you back soon!");
        }

        return 0;
    }

    private void printProducts(List<Category> categories) {
        categories.forEach(c -> {
            System.out.println();
            System.out.println("-- Category: " + c.getName());
            c.getProducts().forEach(p -> System.out.println(p.getName()));
        });
    }

    private String expectCustomerChoice() throws OrderCancelledException, IOException {
        String userChoice = null;

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        while (!"cancel".equalsIgnoreCase(userChoice) && !validProductName(userChoice, categories)) {
            System.out.println();
            System.out.println("Please enter valid product name or enter 'cancel' to cancel your order.");

            userChoice = r.readLine();
        }

        if (userChoice == null) {
            throw new IllegalStateException("User choice is NULL. This should never happen!");
        }

        if (userChoice.equalsIgnoreCase("cancel")) {
            throw new OrderCancelledException();
        }

        return userChoice;
    }

    private boolean validProductName(String userChoice, List<Category> categories) {
        for (Category c : categories) {
            for (Product p : c.getProducts()) {
                if (p.getName().equals(userChoice)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void submitOrderForProcessing(String productName) {
        new Thread(() -> {
            try {
                Thread.sleep((new Random().nextInt(30) + 1) * 1000);

                DataBase.addPurchasedProduct(productName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static class OrderCancelledException extends Exception {}
}
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RandomStorePopulator {
    public static List<Category> generate() {

        Faker faker = new Faker();

        Category beer = new Category("Beer");

        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setName(faker.beer().name());
            product.setRate(faker.number().randomDigitNotZero());
            product.setPrice(faker.number().randomDouble(5, 5, 55));
            beer.addProduct(product);
        }
        Category food = new Category("Food");


        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setName(faker.food().dish());
            product.setRate(faker.number().randomDigitNotZero());
            product.setPrice(faker.number().randomDouble(5, 15, 75));
            food.addProduct(product);
        }


        Category book = new Category("Book");

        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setName(faker.book().genre());
            product.setRate(faker.number().randomDigitNotZero());
            product.setPrice(faker.number().randomDouble(5, 10, 105));
            book.addProduct(product);
        }

        return Arrays.asList(beer, food, book);

    }
}

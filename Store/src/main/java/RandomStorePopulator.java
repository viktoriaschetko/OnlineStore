import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.List;


public class RandomStorePopulator {

    public static List<Category> generate() {

        Faker faker = new Faker();

        Category beer = new Category("Beer");

        for (int i = 0; i < 10; i++) {
            Product product = Product
                    .builder()
                    .name(faker.beer().name())
                    .rate(faker.number().randomDigitNotZero())
                    .price(faker.number().randomDouble(5, 5, 55))
                    .build();

            beer.addProduct(product);
        }
        Category food = new Category("Food");


        for (int i = 0; i < 10; i++) {
            Product product = Product
                    .builder()
                    .name(faker.food().dish())
                    .rate(faker.number().randomDigitNotZero())
                    .price(faker.number().randomDouble(5, 15, 75))
                    .build();

            food.addProduct(product);
        }


        Category book = new Category("Book");

        for (int i = 0; i < 10; i++) {
            Product product = Product
                    .builder()
                    .name(faker.book().genre())
                    .rate(faker.number().randomDigitNotZero())
                    .price(faker.number().randomDouble(5, 10, 105))
                    .build();

            book.addProduct(product);
        }

        return Arrays.asList(beer, food, book);

    }
}

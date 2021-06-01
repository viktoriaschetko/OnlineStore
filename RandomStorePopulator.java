import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;


public class RandomStorePopulator {
   public  RandomStorePopulator () {

       Faker faker = new Faker();
       List<Product> products = new ArrayList<>();

       Category beer = new Category();
       {
           for (int i = 0; i < 10; i++) {
               Product product = new Product();
               product.setName(faker.beer().name());
               product.setRate(faker.number().randomDigitNotZero());
               product.setPrice(faker.number().randomDouble(5, 5, 55));
               products.add(product);
           }
           beer.setProducts(products);
           System.out.println(products);

           Category food = new Category();

           {
               for (int i = 0; i < 10; i++) {
                   Product product = new Product();
                   product.setName(faker.food().dish());
                   product.setRate(faker.number().randomDigitNotZero());
                   product.setPrice(faker.number().randomDouble(5, 15, 75));
                   products.add(product);
               }
               food.setProducts(products);
               System.out.println(products);
           }
           Category book = new Category();
           {
               for (int i = 0; i < 10; i++) {
                   Product product = new Product();
                   product.setName(faker.book().genre());
                   product.setRate(faker.number().randomDigitNotZero());
                   product.setPrice(faker.number().randomDouble(5, 10, 105));
                   products.add(product);
               }
               book.setProducts(products);
               System.out.println(products);
           }
       }
   }
}
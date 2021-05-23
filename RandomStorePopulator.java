
import com.github.javafaker.Faker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RandomStorePopulator {

    public List<Store> generateStores() {
        List<Store>;
        ArrayList<Object> store = new ArrayList<Object>();
        Faker faker = new Faker();


        for (
                int i = 0;
                i <10; i++) {
            Array Beer;
            {
                Beer.add(createObject()
                        .put("hop", faker.beer().hop())
                        .put("malt", faker.beer().malt())
                        .put("name", faker.beer().name())
                        .put("style", faker.beer().style())
                        .put("yeast", faker.beer().yeast()));

                return beer;
            }

            Array Food = createArray();

            for (
                    int i = 0;
                    i < 10; i++) {
                food.add(createObject()
                        .put("ingredients", faker.food().ingredient())
                        .put("spices", faker.food().spice())
                        .put("measurements", faker.food().measurement()));

                return food;
            }

            Array RockBand = createArray();

            for (
                    int i = 0;
                    i < 10; i++) {
                rockBand.add(createObject()
                        .put("name", faker.rockBand().name()));
                return rockBand;
            }
        }
    }
}
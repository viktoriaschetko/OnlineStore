import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.sort;


    public class  Top5Command {
    private List<Category> categories;

        public Top5Command(List<Category> categories) {this.categories = categories;
        }

        public int execute() {
        List<Product> allProducts = new ArrayList<Product>();

        categories.forEach(c -> {this.categories = categories;
        c.getProducts().forEach(p -> {allProducts.add(p);
        });
        Collections.sort(allProducts, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));

        });
        System.out.println(allProducts.subList(0,5));
           return 0;
    }
    }






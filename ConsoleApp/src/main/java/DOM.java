import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DOM {
    private static final String NAME = "name";
    private static final String rate = "rate";
    private static final String price = "price";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        List<Category> categories = RandomStorePopulator.generate();

        System.out.println(categories);

        var products = categories.get(0).getProducts();


    }

    private static String collectInformation(Document document, final String element) {
        NodeList elements = document.getDocumentElement().getElementsByTagName(String.valueOf(element));

        for (int i = 0; i < elements.getLength(); i++) {
            System.out.println(elements.item(i).getTextContent());
            return elements.item(i).getTextContent();

         /*  NamedNodeMap attributes = elements.item(i).getAttributes();
           String name = attributes.getNamedItem("name").getNodeValue();
           String rate = attributes.getNamedItem("rate").getNodeValue();
           String price = attributes.getNamedItem("price").getNodeValue();
           products.add(new Product());
           System.out.println(elements);*/

        }

        return null;
    }
}
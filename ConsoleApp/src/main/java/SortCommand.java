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
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.sort;
import static java.util.Comparator.comparing;

public class SortCommand {

    private final List<Category> categories;

    public SortCommand(List<Category> categories) {
        this.categories = categories;
    }

    public int execute() throws ParserConfigurationException, IOException, SAXException {
        final String nameSort = config("name");
        final String priceSort = config("price");
        final String rateSort = config("rate");

        List<Category> clonedCats = new ArrayList<>(categories.size());

        // clone categories
        categories.forEach(c -> {
            clonedCats.add((Category) c.clone());
        });

        clonedCats.forEach(c -> {
            c.getProducts().sort((p1, p2) -> {
                String nS = nameSort;
                String pS = priceSort;
                String rS = rateSort;

                int count = 0;

                if (nS == null) {
                    nS = "asc";
                }

                if (nS.equals("asc")) {
                    count = p1.getName().compareTo(p2.getName());
                }

                if (nS.equals("desc")) {
                    count = p2.getName().compareTo(p1.getName());
                }

                if (count == 0) {
                    if (pS == null) {
                        pS = "asc";
                    }

                    if (pS.equals("asc")) {
                        count = Double.compare(p1.getPrice(), p2.getPrice());
                    }

                    if (pS.equals("desc")) {
                        count = Double.compare(p2.getPrice(), p1.getPrice());
                    }
                }

                if (count == 0) {
                    if (rS == null) {
                        rS = "asc";
                    }

                    if (rS.equals("asc")) {
                        count = Integer.compare(p1.getRate(), p2.getRate());
                    }

                    if (rS.equals("desc")) {
                        count = Integer.compare(p2.getRate(), p1.getRate());
                    }
                }

                return count;
            });
        });

        System.out.println(clonedCats);

        return 0;
    }

    private String config(String paramName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("/Users/macbookair/IdeaProjects/OnlineStore/ConsoleApp/src/main/resources/config"));
        return collectInformation(document, paramName);
    }

    private static String collectInformation(Document document, final String element) {
        NodeList elements = document.getDocumentElement().getElementsByTagName(String.valueOf(element));

        for (int i = 0; i < elements.getLength(); i++) {
            System.out.println(elements.item(i).getTextContent());
            return elements.item(i).getTextContent();
        }

        return null;
    }
}
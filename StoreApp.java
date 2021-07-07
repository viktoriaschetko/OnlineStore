import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class StoreApp {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        List<Category> categories = RandomStorePopulator.generate();

        printUI();

        expectCommands(categories);
    }

    private static void printUI() {
        System.out.println("""
                Hello!
                                
                Make your choice:
                                
                sort - prints sorted product
                top - prints top 5 most expensive products
                quit - exit
                """);
    }

    private static void expectCommands(List<Category> categories) throws IOException, ParserConfigurationException, SAXException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        CommandHandler h = new CommandHandler();

        int cmdExecutionCode = 0;

        while (cmdExecutionCode == 0) {
            System.out.println("Your choice, mam?");

            String cmd = r.readLine();
            cmdExecutionCode = h.run(cmd, categories);
        }

        System.out.println("Bye bye!!!");
    }
}
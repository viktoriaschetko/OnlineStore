import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class StoreApp {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        printUI();
        expectCommands();
    }

    private static void printUI() {
        System.out.println("""
                Hello!
                                
                Make your choice:
                                
                sort - prints sorted product
                top - prints top 5 most expensive products
                create order - accepts customer's order for processing
                quit - exit
                """);
    }

    private static void expectCommands() throws IOException, ParserConfigurationException, SAXException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        CommandHandler h = new CommandHandler();

        int cmdExecutionCode = 0;

        while (cmdExecutionCode == 0) {
            System.out.println("Your choice, mam?");

            String cmd = r.readLine();
            cmdExecutionCode = h.run(cmd);
        }

        r.close();

        System.out.println("Bye bye!!!");
    }
}
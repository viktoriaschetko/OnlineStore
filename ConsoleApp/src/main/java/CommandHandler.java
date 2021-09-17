import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class CommandHandler {

    public int run(String cmd, List<Category> categories) throws ParserConfigurationException, IOException, SAXException {
        switch (cmd) {
            case "sort":
                return new SortCommand(categories).execute();
            case "top":
                return new Top5Command(categories).execute();
            case "create order":
                return new CreateOrderCommand(categories).execute();
            case "quit":
                return new QuitCommand().execute();
            default:
                System.out.println("Sorry, unknown command, man... :(");
                return 0;
        }
    }
}

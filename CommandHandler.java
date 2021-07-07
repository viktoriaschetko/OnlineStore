import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class CommandHandler {

    public int run(String cmd, List<Category> categories) throws ParserConfigurationException, IOException, SAXException {
        if (cmd.equals("sort")) {
            return new SortCommand(categories).execute();
        } else if (cmd.equals("top")) {
            return new Top5Command(categories).execute();
        } else if (cmd.equals("quit")) {
            return new QuitCommand().execute();
        } else {
            System.out.println("Sorry, unknown command, man... :(");
            return 0;
        }
    }
}

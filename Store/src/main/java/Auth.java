import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderValues;

import java.util.Base64;

public class Auth {

    public static boolean isAuthenticated(HttpServerExchange exchange) {
        HeaderValues authHeaderValues = exchange.getRequestHeaders().get("Authorization");

        if (authHeaderValues != null && authHeaderValues.size() > 0) {
            String auth = authHeaderValues.getFirst();

            String base64 = auth.replace("Basic ", "");

            String[] userNameAndPassword = new String(Base64.getDecoder().decode(base64)).split(":");

            String userName = userNameAndPassword[0];
            String password = userNameAndPassword[1];

            return userName.equals("user") && password.equals("password");
        }

        return false;
    }
}
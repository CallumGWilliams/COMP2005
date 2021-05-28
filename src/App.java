import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

public class App {
    public Object getData() throws IOException, ParseException {
        URL url = new URL("http://intelligent-social-robots-ws.com/restaurant-data.json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int responseCode = conn.getResponseCode();
    return responseCode;
    }

}

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

public class App {
    public Object getData() throws IOException, ParseException, org.json.simple.parser.ParseException {
        URL url = new URL("http://intelligent-social-robots-ws.com/restaurant-data.json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int responseCode = conn.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            Object obj = new JSONParser().parse(new InputStreamReader(conn.getInputStream()));
            JSONObject jo = (JSONObject) obj;

            Integer id = (Integer) jo.get("id");
            String n = (String) jo.get("name"); //returns null, has no field for "name" (NPE) (or any other keyword)

            JSONArray a = (JSONArray) jo.get("restaurants"); //only way to return data using .get - returns json object

            System.out.println(id); //prints null
            System.out.println(n); //also prints null
            System.out.println(a); //prints array of restaurants (inc. all dependant data)
            return jo;

        }
        return responseCode;
    }
}

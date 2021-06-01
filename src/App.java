import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.json.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;

public class App {

    public Object getData() throws IOException, ParseException, org.json.simple.parser.ParseException {
        URL url = new URL("http://intelligent-social-robots-ws.com/restaurant-data.json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int responseCode = conn.getResponseCode();

            Object obj = new JSONParser().parse(new InputStreamReader(conn.getInputStream()));
            JSONObject jo = (JSONObject) obj;
            JSONArray ja = (JSONArray) jo.get("restaurants");


            return ja;

            }

            public void getByCuisineAndNeighbourhood(JSONArray ja, String cuisine, String chosenNeighbourhood){


                for(int i = 0; i < ja.size(); i++){ ;
                    JSONObject restaurant = (JSONObject) ja.get(i);


                    String cuisine_type = (String) restaurant.get("cuisine_type");
                    String restaurantName = (String) restaurant.get("name");
                    String neighbourhood = (String) restaurant.get("neighborhood");


                    if(cuisine_type.equals(cuisine) & neighbourhood.equals(chosenNeighbourhood)) {
            System.out.println("Generating restaurants with cuisine: " + cuisine + " in the neighbourhood: " + chosenNeighbourhood );
                            System.out.println(restaurantName);
                    }

            }
    }

    public void getByOpeningHours(){
        
    }

}



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
import java.util.Map;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;

public class App {

    public Object getData() throws IOException, ParseException, org.json.simple.parser.ParseException {
        URL url = new URL("http://intelligent-social-robots-ws.com/restaurant-data.json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int responseCode = conn.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            Object obj = new JSONParser().parse(new InputStreamReader(conn.getInputStream()));
            JSONObject jo = (JSONObject) obj;
            JSONArray ja = (JSONArray) jo.get("restaurants");

            for(int i = 0; i < ja.size(); i++){ ;
                JSONObject restaurant = (JSONObject) ja.get(i);

               String photograph = (String) restaurant.get("photograph");
               String dohmhScore = (String) restaurant.get("DOHMH_inspection_score");
               String address = (String) restaurant.get("address");
               String cuisine_type = (String) restaurant.get("cuisine_type");
               String restaurantName = (String) restaurant.get("name");
               long id = Long.parseLong(String.valueOf(restaurant.get("id")));
               String neighbourhood = (String) restaurant.get("neighbourhood");


               JSONObject latlng = (JSONObject) restaurant.get("latlng");
               double lng = Double.parseDouble(String.valueOf(latlng.get("lng")));
               double lat = Double.parseDouble(String.valueOf(latlng.get("lat")));

               JSONObject operatingHours = (JSONObject) restaurant.get("operating_hours");
               String mon = (String) operatingHours.get("Monday");
               String tue = (String) operatingHours.get("Tuesday");
               String wed = (String) operatingHours.get("Wednesday");
               String thu = (String) operatingHours.get("Thursday");
               String fri = (String) operatingHours.get("Friday");
               String sat = (String) operatingHours.get("Saturday");
               String sun = (String) operatingHours.get("Sunday");

               JSONArray reviews = (JSONArray) restaurant.get("reviews");

               for (int e = 0; e < reviews.size(); e++) {
                   JSONObject review = (JSONObject) reviews.get(e);
                   String date = (String) review.get("date");
                   String comments = (String) review.get("comments");
                   String revName = (String) review.get("name");
                   long rating = Long.parseLong(String.valueOf(review.get("rating")));
               }

            }
            return null;



        }
        return responseCode;
    }
}

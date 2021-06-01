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

    public void getByCuisineAndNeighbourhood(JSONArray ja, String cuisine, String chosenNeighbourhood) {


        for (int i = 0; i < ja.size(); i++) {
            ;
            JSONObject restaurant = (JSONObject) ja.get(i);


            String cuisine_type = (String) restaurant.get("cuisine_type");
            String restaurantName = (String) restaurant.get("name");
            String neighbourhood = (String) restaurant.get("neighborhood");


            if (cuisine_type.equals(cuisine) & neighbourhood.equals(chosenNeighbourhood)) {
                System.out.println("Generating restaurants with cuisine: " + cuisine + " in the neighbourhood: " + chosenNeighbourhood);
                System.out.println(restaurantName);
            }

        }
    }

    public void getByOpeningHours(JSONArray ja, String day) {
        System.out.println("Generating restaurants open on: " + day);
        for (int i = 0; i < ja.size(); i++) {
            ;
            JSONObject restaurant = (JSONObject) ja.get(i);


            String restaurantName = (String) restaurant.get("name");

            JSONObject operatingHours = (JSONObject) restaurant.get("operating_hours");


            if (!operatingHours.get(day).equals("Closed")) {

                System.out.println(restaurantName + ": " + operatingHours.get(day));
            }


        }
    }

    public void getByReviewRating(JSONArray ja, String chosenNeighbourhood, int reviewRating) {
        for (int i = 0; i < ja.size(); i++) {
            ;
            JSONObject restaurant = (JSONObject) ja.get(i);
            String neighbourhood = (String) restaurant.get("neighborhood");
            String restaurantName = (String) restaurant.get("name");

            JSONArray reviews = (JSONArray) restaurant.get("reviews");

            for (int e = 0; e < reviews.size(); e++) {
                JSONObject review = (JSONObject) reviews.get(e);
                long rating = Long.parseLong(String.valueOf(review.get("rating")));
                String revName = (String) review.get("name");


                if (neighbourhood.equals(chosenNeighbourhood)) {

                    if (rating >= reviewRating) {
                        System.out.println(restaurantName + ": Rating: " + rating + " review by: " + revName);
                    }
                }
            }
        }

    }

    public void getByDohmh(JSONArray ja, String chosenNeighbourhood) {
        for (int i = 0; i < ja.size(); i++) {
            ;
            JSONObject restaurant = (JSONObject) ja.get(i);
            String neighbourhood = (String) restaurant.get("neighborhood");
            String restaurantName = (String) restaurant.get("name");
            String dohmh = (String) restaurant.get("DOHMH_inspection_score");


            if (neighbourhood.equals(chosenNeighbourhood)) {

                System.out.println(dohmh + " " + restaurantName);
            }

        }
    }

    public void getNearHotel(JSONArray ja, String neighbourhood){
        

    }
}



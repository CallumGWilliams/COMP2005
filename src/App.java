import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.json.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

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

    public JSONArray getByCuisineAndNeighbourhood(JSONArray ja, String cuisine, String chosenNeighbourhood) {
        int c = 0;
        JSONArray a = new JSONArray();
        System.out.println("Generating restaurants with cuisine: " + cuisine + " in the neighbourhood: " + chosenNeighbourhood);

        for (int i = 0; i < ja.size(); i++) {
            JSONObject restaurant = (JSONObject) ja.get(i);

            String cuisine_type = (String) restaurant.get("cuisine_type");
            String restaurantName = (String) restaurant.get("name");
            String neighbourhood = (String) restaurant.get("neighborhood");


            if (cuisine_type.equals(cuisine) & neighbourhood.equals(chosenNeighbourhood)) {

                System.out.println(restaurantName);
                a.add(restaurant);
                c++;
            }

        }
        if (c == 0) {
            System.out.println("Neighbourhood: " + chosenNeighbourhood + " With cuisine type: " + cuisine + " does not exist");
        }
        return a;
    }

    public JSONArray getByOpeningHours(JSONArray ja, String day) {
        int c = 0;
        JSONArray a = new JSONArray();
        System.out.println("Generating restaurants open on: " + day);
        for (int i = 0; i < ja.size(); i++) {
            ;
            JSONObject restaurant = (JSONObject) ja.get(i);


            String restaurantName = (String) restaurant.get("name");

            JSONObject operatingHours = (JSONObject) restaurant.get("operating_hours");


            if (!operatingHours.get(day).equals("Closed")) {

                System.out.println(restaurantName + ": " + operatingHours.get(day));
                c++;
                a.add(restaurant);
            }


        }
        return a;
    }

    public JSONArray getByReviewRating(JSONArray ja, String chosenNeighbourhood, int reviewRating) {
        int c = 0;
        JSONArray a = new JSONArray();
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
                        c++;
                        a.add(restaurant);
                    }
                }
            }
        }
        return a;
    }

    public JSONArray getByDohmh(JSONArray ja, String chosenNeighbourhood) {
        int c = 0; //gets number of entries returned
        JSONArray a = new JSONArray();
        for (int i = 0; i < ja.size(); i++) {

            JSONObject restaurant = (JSONObject) ja.get(i);
            String neighbourhood = (String) restaurant.get("neighborhood");
            String restaurantName = (String) restaurant.get("name");
            String dohmh = (String) restaurant.get("DOHMH_inspection_score");


            if (neighbourhood.equals(chosenNeighbourhood)) {

                System.out.println(dohmh + " " + restaurantName);
                c++;
                a.add(restaurant);

            }

        }
        return a;
    }

    public void getNearHotel(JSONArray ja, String chosenNeighbourhood) {
        double neighLat = 0;
        double neighLng = 0;

        if (chosenNeighbourhood.equals("Brooklyn")) {

            neighLat = 40.689510;
            neighLng = -73.988100;

        }

        if (chosenNeighbourhood.equals("Manhattan")) {

            neighLat = 40.752831;
            neighLng = -73.985748;
        }

        if (chosenNeighbourhood.equals("Queens")) {
            neighLat = 40.753990;
            neighLng = -73.949240;
        }

        for (int i = 0; i < ja.size(); i++) {

            JSONObject restaurant = (JSONObject) ja.get(i);
            String restaurantName = (String) restaurant.get("name");
            String neighbourhood = (String) restaurant.get("neighbourhood");


            JSONObject latlng = (JSONObject) restaurant.get("latlng");
            double lng = Double.parseDouble(String.valueOf(latlng.get("lng")));
            double lat = Double.parseDouble(String.valueOf(latlng.get("lat")));

            double distance = distance(lng, lat, neighLat, neighLng);
            System.out.println(restaurantName + ": " + distance + "Km");

        }
    }

    private double distance(double lat1, double lng1, double lat2, double lng2) {
        double r = 6372.8;

        double lat = Math.toRadians(lat2 - lat1);
        double lng = Math.toRadians(lng2 - lng1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(lat / 2), 2) + Math.pow(Math.sin(lng / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return r * c;
    }
}

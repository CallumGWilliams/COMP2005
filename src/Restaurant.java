import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class Restaurant {


    String photograph;
    String dohmhScore;
    String address;
    String cuisine_type;
    long id;
    String neighbourhood;
    double lng;
    double lat;
    String mon;
    String tue;
    String wed;
    String thu;
    String Fri;
    String sat;
    String sun;
    String date;
    String comments;
    String revName;
    long rating;
    JSONObject opening_hours;

    HashMap<String,String> latlng;
    JSONArray reviews;

    public Restaurant(String photograph, String dohmhScore, String address, String cuisine_type, long id, String neighbourhood, double lng, double lat, JSONArray reviews, JSONObject opening_hours){
        this.photograph = photograph;
        this.dohmhScore = dohmhScore;
        this.address = address;
        this.cuisine_type = cuisine_type;
        this.id = id;
        this.neighbourhood = neighbourhood;
        this.lng = lng;
        this.lat = lat;
        this.reviews = reviews;
        this.opening_hours = opening_hours;
    }


}
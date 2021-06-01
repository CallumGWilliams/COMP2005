import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;

public class TestClass {

    //unit test - testing http connection
    @Test
    public void testCon() throws IOException, java.text.ParseException, ParseException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();
        System.out.println(o);

    }
    @Test
    public void testCuisine() throws java.text.ParseException, ParseException, IOException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();
        int numEntries = app.getByCuisineAndNeighbourhood(o, "Asian","Manhattan");
        assertEquals(2, numEntries);
    }
    @Test
    public void testReview() throws java.text.ParseException, ParseException, IOException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();
       int numEntries =  app.getByReviewRating(o, "Queens", 2);
        assertEquals(6, numEntries);
    }

    @Test
    public void testOpeningDay() throws java.text.ParseException, ParseException, IOException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();
        int numEntries = app.getByOpeningHours(o, "Tuesday");
        assertEquals(9, numEntries);
    }

    @Test
    public void testDohmh() throws java.text.ParseException, ParseException, IOException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();
        int numEntries = (app.getByDohmh(o, "Brooklyn"));
        assertEquals(3, numEntries);
    }
    @Test
    public void testReviewBVA() throws java.text.ParseException, ParseException, IOException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();
        app.getByReviewRating(o, "Queens", 1);
    }

    @Test
    public void testWrongArea() throws java.text.ParseException, ParseException, IOException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();
        app.getByCuisineAndNeighbourhood(o, "Asian", "Brooklyn");
    }

    @Test
    public void testNearestHotel(){

    }

    @Test
    public void integrationTestCuisine() throws java.text.ParseException, ParseException, IOException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();

        int pizzaEntries = app.getByCuisineAndNeighbourhood(o, "Pizza", "Brooklyn");
        assertEquals(2, pizzaEntries);

        int mondayEntries = app.getByOpeningHours(o, "Monday");

        assertEquals(9, mondayEntries);


}}

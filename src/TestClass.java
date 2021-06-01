import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


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
        JSONArray numEntries = app.getByCuisineAndNeighbourhood(o, "Asian","Manhattan");
        assertEquals(2, numEntries.size());
    }
    @Test
    public void testReview() throws java.text.ParseException, ParseException, IOException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();
       JSONArray numEntries =  app.getByReviewRating(o, "Queens", 2);
        assertEquals(6, numEntries.size());
    }

    @Test
    public void testOpeningDay() throws java.text.ParseException, ParseException, IOException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();
        JSONArray numEntries = app.getByOpeningHours(o, "Tuesday");
        //assertEquals(9, numEntries);
    }

    @Test
    public void testDohmh() throws java.text.ParseException, ParseException, IOException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();
        JSONArray numEntries = (app.getByDohmh(o, "Brooklyn"));
        assertEquals(3, numEntries.size());
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
        JSONArray j = app.getByCuisineAndNeighbourhood(o, "Asian", "Brooklyn");
        assertEquals(0,j.size());
    }

    @Test
    public void testNearestHotel() throws java.text.ParseException, ParseException, IOException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();
        app.getNearHotel(o,"Brooklyn");

    }

    @Test
    public void integrationTestCuisine() throws java.text.ParseException, ParseException, IOException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();

        JSONArray american = (JSONArray) app.getByCuisineAndNeighbourhood(o,"American","Manhattan");

        JSONArray americanTues = (JSONArray) app.getByOpeningHours(american,"Tuesday"); //will return two JSON Objects

        assertEquals(2,americanTues.size());


}

    @Test
    public void integrationTestRatingCuisine() throws java.text.ParseException, ParseException, IOException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();

        JSONArray pizza = app.getByCuisineAndNeighbourhood(o,"Pizza","Brooklyn");

        JSONArray goodPizza = app.getByReviewRating(pizza,"Brooklyn",4);

        assertEquals(6, goodPizza.size()); //number of reviews 4+
    }

//edge test for min and max for rating scores
    @Test
    public void testEdgeRating() throws java.text.ParseException, ParseException, IOException {
        App app = new App();
        JSONArray o = (JSONArray) app.getData();

        JSONArray numEntriesZero =  app.getByReviewRating(o, "Brooklyn", 0);

        JSONArray numEntriesFive = app.getByReviewRating(o, "Brooklyn", 5);

        assertNotEquals(numEntriesFive.size(), numEntriesZero.size());



    }

}

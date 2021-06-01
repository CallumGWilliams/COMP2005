import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

public class TestClass {

    //unit test - testing http connection
    @Test
    public void testCon() throws IOException, java.text.ParseException, ParseException {
        App app = new App();
        //Restaurant r = (Restaurant) app.getData();
        ///System.out.println(r.opening_hours);
        JSONArray o = (JSONArray) app.getData();
        //System.out.println(o);
        app.getByCuisineAndNeighbourhood(o, "Asian","Manhattan");

    }
}

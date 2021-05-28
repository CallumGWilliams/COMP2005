import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

public class TestClass {

    @Test
    public void testCon() throws IOException, java.text.ParseException, ParseException {
        App app = new App();
        Object o = app.getData();
        System.out.println(o);

    }
}

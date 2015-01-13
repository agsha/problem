package problem;


import com.jsonfixture.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static final Logger log = LogManager.getLogger(App.class.getName());
    @Test
    public void testApp() throws Exception {
        Main main = new Main();
        main.initialize();
        log.debug(main.getJsonString("com.example.Order", "order1"));
    }
}

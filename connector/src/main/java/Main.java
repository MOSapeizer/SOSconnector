import Connector.Connector;
import Swcb.SwcbRainConnector;

import java.io.IOException;
import java.util.Timer;
import java.util.logging.Logger;

/**
 * Created by Zil on 2016/9/26.
 */
public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());
    private static int sec = 1000;
    private static int min = 60 * sec;
    private static int hour = 60 * min;
    private static int day = 24 * hour;

    public static void main(String[] args) throws IOException {
//        Connector connector = new TwedConnector("http://localhost:8080/ccd/service");
//        Connector connector = new SwcbConnector("http://localhost:8080/swcb-ccd/service");
        Connector connector = new SwcbRainConnector("http://localhost:8080/swcb-sos/service");
        Timer timer = new Timer();
        timer.schedule(connector, 5 * sec, 10 * min);
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }
}

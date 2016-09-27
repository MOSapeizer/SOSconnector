import Twed.TwedConnector;

import java.io.IOException;
import java.util.Timer;

/**
 * Created by Zil on 2016/9/26.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        TwedConnector twedConnector = new TwedConnector();
        Timer timer = new Timer();
        timer.schedule(twedConnector, 5 * 1000, 10 * 60 * 1000);

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

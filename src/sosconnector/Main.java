package sosconnector;

import javax.xml.bind.JAXBException;
import java.util.Date;
import java.util.Timer;

/**
 * Created by zil on 2016/4/21.
 */
public class Main {

    private final static int seconds = 1000;
    private final static int minutes = 60 * seconds;
    private final static int hours = 60 * minutes;
    private final static int days = 24 * hours;

    public static void main(String[] args) throws JAXBException {
        Timer timer = new Timer( true );
//        timer.schedule(new Twed(), 5 * seconds, 10 * minutes);
//        timer.schedule(new CwbSea(), 5 * seconds, 1 * days);
//        timer.schedule(new Swcb(), 15 * seconds, 7 * days);
        System.out.println("現在時間：" + new Date());

        try {
            Thread.sleep(365 * days);
        } catch (InterruptedException ignored) {
        }

        System.out.println("結束時間：" + new Date());
        timer.cancel();
    }
}

package sosconnector;

import sosconnector.Adapter.SosFactory;
import sosconnector.DAO.SwcbDAO;
import java.util.TimerTask;

/**
 * Created by zil on 2016/4/29.
 */
public class Swcb extends TimerTask {
    private final String url = "http://data.wra.gov.tw/Service/OpenData.aspx?id=2D09DB8B-6A1B-485E-88B5-923A462F475C&format=xml";
    private final String service = "http://localhost:8080/twed_waterLevel/service";

    @Override
    public void run() {
        SwcbDAO swcb = new SwcbDAO(url);
        new SosFactory<>( swcb, service ).work();
    }
}

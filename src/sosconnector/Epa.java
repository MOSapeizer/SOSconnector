package sosconnector;

import sosconnector.Adapter.SosFactory;
import sosconnector.DAO.EpaRainDAO;

import java.util.TimerTask;

/**
 * Created by zil on 2016/6/1.
 */
public class Epa extends TimerTask {
    private final String url = "http://opendata.epa.gov.tw/ws/Data/RainTenMin/?$orderby=PublishTime%20desc&$skip=0&$top=1000&format=xml";
    private final String filePath = "xml/EpaRain.xml";
    private final String service = "http://localhost:8080/epa-sos/service";

    @Override
    public void run() {
        EpaRainDAO swcb = new EpaRainDAO(url, filePath);
        new SosFactory<>( swcb, service ).work();
    }
}

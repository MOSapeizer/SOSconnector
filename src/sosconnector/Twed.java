package sosconnector;

import sosconnector.Adapter.SosAdapter;
import sosconnector.Adapter.SosFactory;
import sosconnector.DAO.TwedDAO;
import sosconnector.Filter.Filter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.util.LinkedList;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/4/20.
 */
public class Twed extends TimerTask {

    private final String url = "http://data.wra.gov.tw/Service/OpenData.aspx?id=2D09DB8B-6A1B-485E-88B5-923A462F475C&format=xml";
    private final String filePath = "xml/twed.xml";
    private final String service = "http://localhost:8080/twed_waterLevel/service";

    @Override
    public void run() {
        TwedDAO twed = new TwedDAO(url, filePath);
        new SosFactory<>( twed, service ).work();
    }
}
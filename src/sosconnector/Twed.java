package sosconnector;

import sosconnector.Adapter.SosAdapter;
import sosconnector.DAO.TwedDAO;
import sosconnector.Filter.Filter;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/4/20.
 */
public class Twed extends TimerTask {

    private final static String url = "http://data.wra.gov.tw/Service/OpenData.aspx?id=2D09DB8B-6A1B-485E-88B5-923A462F475C&format=xml";
    private final static String filePath = "src/sosconnector/GovConfigure/twed.xml";
    private final static String service = "http://localhost:8080/twed_waterLevel/service";
    private SosAdapter sos = new SosAdapter(service);
    private Filter filter;

    public void run(){
        TwedDAO twed = new TwedDAO(url, filePath);
        try {
            LinkedList<String> sensorGroup = twed.getInsertSensorXML();
            clean( sensorGroup );
            send( sensorGroup );

            LinkedList<String> observationXML = twed.getInsertObservationXML();
            send( observationXML );
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            Logger.getLogger(Twed.class.getName()).log(Level.SEVERE, "Can't get Xml", e);;
        }
    }

    private void clean(LinkedList<String> list){
        if( filter == null )
            filter = new Filter(list);
        else
            filter.compare(list);
    }

    private void send(LinkedList<String> list){
        for(String xml: list)
            sos.insert(xml);
    }
}
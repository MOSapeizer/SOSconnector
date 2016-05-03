package sosconnector.Adapter;

import sosconnector.DAO.DAOFactory;
import sosconnector.Filter.Analyze;
import sosconnector.Filter.Filter;
import sosconnector.Twed;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/4/22.
 */
public class SosFactory<T extends DAOFactory> {

    private SosAdapter sos;
    private final T dao;
    private Filter filter;

    public SosFactory(T dao, String service) {
        this.dao = dao;
        sos = new SosAdapter(service);
    }

    public void work(){
        try {
            LinkedList<String> sensorGroup = dao.getInsertSensorXML();
            clean( sensorGroup );
            send( sensorGroup );

            LinkedList<String> observationXML = dao.getInsertObservationXML();
            send( observationXML );
            System.out.println("\nTime in Class " + dao.getClass().getName() + ": " + new Date() + Analyze.result() );
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            Logger.getLogger(SosFactory.class.getName()).log(Level.SEVERE, "Can't get Xml", e);;
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

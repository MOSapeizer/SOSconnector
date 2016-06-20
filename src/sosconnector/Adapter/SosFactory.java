package sosconnector.Adapter;

import sosconnector.DAO.XmlDAO;
import sosconnector.Filter.Analyze;
import sosconnector.Filter.Filter;

import java.util.Date;
import java.util.LinkedList;

/**
 * Created by zil on 2016/4/22.
 */
public class SosFactory {

    private SosAdapter sos;
    private XmlDAO dao;
    private Filter filter;

    public SosFactory(XmlDAO dao, String service) {
        this.dao = dao;
        sos = new SosAdapter(service);
    }

    public void work(){
        LinkedList<String> sensorGroup = dao.getInsertSensorXML();
        clean( sensorGroup );
        send( sensorGroup );

        LinkedList<String> observationXML = dao.getInsertObservationXML();
        send( observationXML );
        System.out.println("\nTime in Class " + dao.getClass().getName() + ": " + new Date() + Analyze.result() );
    }

    public void update(){
        LinkedList<String> observationXML = null;
        observationXML = dao.getInsertObservationXML();
        send( observationXML );
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

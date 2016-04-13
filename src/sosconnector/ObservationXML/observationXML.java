package sosconnector.ObservationXML;

/**
 * Created by zil on 2016/4/13.
 */
public interface ObservationXML {
    String getInsertSensorXml(String siteName);
    String getInsertObservationXML();
    StringBuffer allObservations();
}

package sosconnector.Request;

import sosconnector.ObservationXML.ObservationXML;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Created by zil on 2016/3/30.
 */
public class InsertRequest extends Request {

    public InsertRequest(String urlString) throws IOException {
        super(urlString);
        this.connection = setConnection();
    }

    public static String getInsertSensorXML(Object obj){

        return null;
    }

    public static String getInsertObservationXml(String stationName, StringBuffer allObsString) {
        String insertobservationxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<sos:InsertObservation\n"
                + "    xmlns:sos=\"http://www.opengis.net/sos/2.0\"\n"
                + "    xmlns:swes=\"http://www.opengis.net/swes/2.0\"\n"
                + "    xmlns:swe=\"http://www.opengis.net/swe/2.0\"\n"
                + "    xmlns:sml=\"http://www.opengis.net/sensorML/1.0.1\"\n"
                + "    xmlns:gml=\"http://www.opengis.net/gml/3.2\"\n"
                + "    xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n"
                + "    xmlns:om=\"http://www.opengis.net/om/2.0\"\n"
                + "    xmlns:sams=\"http://www.opengis.net/samplingSpatial/2.0\"\n"
                + "    xmlns:sf=\"http://www.opengis.net/sampling/2.0\"\n"
                + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" service=\"SOS\" version=\"2.0.0\""
                + " xsi:schemaLocation=\"http://www.opengis.net/sos/2.0 http://schemas.opengis.net/sos/2.0/sos.xsd"
                + " http://www.opengis.net/samplingSpatial/2.0 http://schemas.opengis.net/samplingSpatial/2.0/spatialSamplingFeature.xsd\">\n"
                + "    <sos:offering>" + stationName + "</sos:offering>\n"
                + allObsString
                + "</sos:InsertObservation>";
        return insertobservationxml;
    }

    public HttpURLConnection setConnection() throws IOException {
        HttpURLConnection connection = super.setConnection("POST");
        connection.setRequestProperty("Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
        return connection;
    }


}

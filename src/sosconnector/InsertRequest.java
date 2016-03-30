package sosconnector;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.in;

/**
 * Created by zil on 2016/3/30.
 */
public class InsertRequest {

    public URL url;
    public HttpURLConnection connection;

    private final static String USER_AGENT = "Mozilla/5.0";

    public InsertRequest(String urlString) throws IOException {
        this.url = setURL( urlString );
        this.connection = setConnection();
    }

    protected URL setURL(String urlString) {
        try {
            return  new URL(urlString);
        } catch (MalformedURLException e) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    protected static String insertSensorXml(String stationName) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<swes:InsertSensor\n"
                + "    xmlns:swes=\"http://www.opengis.net/swes/2.0\"\n"
                + "    xmlns:sos=\"http://www.opengis.net/sos/2.0\"\n"
                + "    xmlns:swe=\"http://www.opengis.net/swe/1.0.1\"\n"
                + "    xmlns:sml=\"http://www.opengis.net/sensorML/1.0.1\"\n"
                + "    xmlns:gml=\"http://www.opengis.net/gml\"\n"
                + "    xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n"
                + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" service=\"SOS\" version=\"2.0.0\" xsi:schemaLocation=\"http://www.opengis.net/sos/2.0 http://schemas.opengis.net/sos/2.0/sosInsertSensor.xsd   http://www.opengis.net/swes/2.0 http://schemas.opengis.net/swes/2.0/swes.xsd\">\n"
                + "    <swes:procedureDescriptionFormat>http://www.opengis.net/sensorML/1.0.1</swes:procedureDescriptionFormat>\n"
                + "    <swes:procedureDescription>\n"
                + "        <sml:SensorML version=\"1.0.1\">\n"
                + "            <sml:member>\n"
                + "                <sml:System>\n"
                + "                    <!-- optional; generated if not present -->\n"
                + "                    <sml:identification>\n"
                + "                        <sml:IdentifierList>\n"
                + "                            <sml:identifier name=\"uniqueID\">\n"
                + "                                <sml:Term definition=\"urn:ogc:def:identifier:OGC:1.0:uniqueID\">\n"
                + "                                    <sml:value>urn:ogc:object:feature:Sensor:EPA:sensor" + stationName + "</sml:value>\n"
                + "                                </sml:Term>\n"
                + "                            </sml:identifier>\n"
                + "                        </sml:IdentifierList>\n"
                + "                    </sml:identification>\n"
                + "                    <sml:capabilities name=\"offerings\">\n"
                + "                        <swe:SimpleDataRecord>\n"
                + "                            <!-- Field name or gml:name is used for the offering's name -->\n"
                + "                            <swe:field name=\"" + stationName + "\">\n"
                + "                                <swe:Text definition=\"urn:ogc:def:identifier:OGC:offeringID\">\n"
                + "                                    <gml:name>" + stationName + "</gml:name>\n"
                + "                                    <swe:value>" + stationName + "</swe:value>\n"
                + "                                </swe:Text>\n"
                + "                            </swe:field>\n"
                + "                        </swe:SimpleDataRecord>\n"
                + "                    </sml:capabilities>\n"
                + "                </sml:System>\n"
                + "            </sml:member>\n"
                + "        </sml:SensorML>\n"
                + "    </swes:procedureDescription>\n"
                + "    <!-- multiple values possible -->\n"
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_PSI</swes:observableProperty>\n"
                + "  <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_SO2</swes:observableProperty>\n"
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_CO</swes:observableProperty>\n"
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_O3</swes:observableProperty>\n"
                + "  <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_PM10</swes:observableProperty>\n"
                + "  <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_PM2.5</swes:observableProperty>\n"
                + "  <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_NO2</swes:observableProperty>\n"
                + "  <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_WindSpeed</swes:observableProperty>\n"
                + "  <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_WindDirec</swes:observableProperty>\n"
                + "  <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_FPMI</swes:observableProperty>\n"
                + "  <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_NOx</swes:observableProperty>\n"
                + "  <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_NO</swes:observableProperty>\n"
                + "    <swes:metadata>\n"
                + "        <sos:SosInsertionMetadata>\n"
                + "            <sos:observationType>http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement</sos:observationType>\n"
                + "            <!-- multiple values possible -->\n"
                + "           \n"
                + "        </sos:SosInsertionMetadata>\n"
                + "    </swes:metadata>\n"
                + "</swes:InsertSensor>";
        return xml;

    }

    protected static String insertObservationXml(String stationName, StringBuffer allObsString) {
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
                + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" service=\"SOS\" version=\"2.0.0\" xsi:schemaLocation=\"http://www.opengis.net/sos/2.0 http://schemas.opengis.net/sos/2.0/sos.xsd          http://www.opengis.net/samplingSpatial/2.0 http://schemas.opengis.net/samplingSpatial/2.0/spatialSamplingFeature.xsd\">\n"
                + "    <!-- multiple offerings are possible -->\n"
                + "    <sos:offering>" + stationName + "</sos:offering>\n"
                + allObsString
                + "</sos:InsertObservation>";
        return insertobservationxml;
    }

    public HttpURLConnection setConnection() throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true); // Triggers POST.
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/xml");
        connection.setRequestProperty("Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
//        connection.setRequestProperty("User-Agent", USER_AGENT);

        return connection;
    }

    public void writeIn(String input) throws IOException {
        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        wr.write( input );
        wr.flush();
        wr.close();
    }

    private InputStreamReader getConnectionReader() throws IOException {
        return new InputStreamReader(connection.getInputStream(), "UTF-8");
    }

    private BufferedReader getBufferedReader() throws IOException {
        return new BufferedReader( getConnectionReader() );
    }

    public String getResponseBody() throws IOException{
        BufferedReader in = getBufferedReader();
        return readFrom(in);
    }

    protected String readFrom(BufferedReader in) throws IOException {
        String inputLine;
        StringBuffer out = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            out.append(inputLine);
        }
        in.close();
        return out.toString();
    }
}

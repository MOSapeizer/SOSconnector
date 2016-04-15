package sosconnector;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SOSConnector extends TimerTask {

    private final static String USER_AGENT = "Mozilla/5.0";

    @Override
    public void run() {
        getStation();
        getRain();
        getrainFromEPA();
//        System.out.println("Task 執行時間：" + new Date());
    }

    public void getStation() {
        try {
            String swcburl = "http://localhost:8080/swcb-sos/service";
//            String url = "http://dfm.swcb.gov.tw/DebrisService/InformationService.asmx/GetStation";
//            URL obj = new URL(url);
////            String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
////            URLConnection connection = new URL(url).openConnection();
//            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
//            connection.setDoOutput(true); // Triggers POST.
//            connection.setRequestMethod("POST");
////            connection.setRequestProperty("Accept-Charset", charset);
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
////            connection.setRequestProperty("User-Agent", USER_AGENT);
//            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
////            wr.writeBytes(messageBody);
//            wr.flush();
//            wr.close();
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            String str = response.toString();
//
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder;
//            InputSource is;
//            is = new InputSource(new StringReader(str));
//            builder = factory.newDocumentBuilder();
//            org.w3c.dom.Document doc = builder.parse(is);

            File fXmlFile = new File("GetStation.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Row");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String sid = eElement.getElementsByTagName("StationID").item(0).getTextContent();
                    String name = eElement.getElementsByTagName("StationName").item(0).getTextContent();
                    String en_name = eElement.getElementsByTagName("StationEnglishName").item(0).getTextContent();
                    String lon = eElement.getElementsByTagName("Longitude").item(0).getTextContent();
                    String lat = eElement.getElementsByTagName("Latitude").item(0).getTextContent();
                    String district = eElement.getElementsByTagName("District").item(0).getTextContent();
                    String debrisNO = eElement.getElementsByTagName("DebrisNO").item(0).getTextContent();
                    String disaster = eElement.getElementsByTagName("Disaster").item(0).getTextContent();
                    String river = eElement.getElementsByTagName("River").item(0).getTextContent();
                    String drainage = eElement.getElementsByTagName("Drainage").item(0).getTextContent();
                    String catchment = eElement.getElementsByTagName("Catchment").item(0).getTextContent();
                    String subcatchment = eElement.getElementsByTagName("SubCatchment").item(0).getTextContent();
                    String en_district = eElement.getElementsByTagName("EngDistrict").item(0).getTextContent();
                    String en_debrisNO = eElement.getElementsByTagName("EngDebrisNO").item(0).getTextContent();
                    String en_disaster = eElement.getElementsByTagName("EngDisaster").item(0).getTextContent();
                    String en_river = eElement.getElementsByTagName("EngRiver").item(0).getTextContent();
                    String en_drainage = eElement.getElementsByTagName("EngDrainage").item(0).getTextContent();
                    String en_catchment = eElement.getElementsByTagName("EngCatchment").item(0).getTextContent();
                    String en_subcatchment = eElement.getElementsByTagName("EngSubCatchment").item(0).getTextContent();
                    String imgURL = eElement.getElementsByTagName("ImageURL").item(0).getTextContent();
                    String stationURL = eElement.getElementsByTagName("StationURL").item(0).getTextContent();

                    if (DBManager.getInstance().ifStationRedundant("swcb_station", sid) == -1) {
                        en_name = en_name.replace("'", "\\'");
                        en_district = en_district.replace("'", "\\'");
                        en_debrisNO = en_debrisNO.replace("'", "\\'");
                        en_disaster = en_disaster.replace("'", "\\'");
                        en_river = en_river.replace("'", "\\'");
                        en_drainage = en_drainage.replace("'", "\\'");
                        en_catchment = en_catchment.replace("'", "\\'");
                        en_subcatchment = en_subcatchment.replace("'", "\\'");
                        DBManager.getInstance().insertStation_swcb(sid, name, en_name, lon, lat, district, debrisNO, disaster, river, drainage, catchment, subcatchment, en_district, en_debrisNO, en_disaster, en_river, en_drainage, en_catchment, en_subcatchment, imgURL, stationURL);
                        sendInsertsensorRequest(name,swcburl,"urn:ogc:def:phenomenon:OGC:2.0:rainfall_1day");
                    }
                }
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getRain() {
        try {
            String swcburl = "http://localhost:8080/swcb-sos/service";
//            String url = "http://dfm.swcb.gov.tw/DebrisService/InformationService.asmx/GetAllStationRain";
//            URL obj = new URL(url);
////            String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
////            URLConnection connection = new URL(url).openConnection();
//            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
//            connection.setDoOutput(true); // Triggers POST.
//            connection.setRequestMethod("POST");
////            connection.setRequestProperty("Accept-Charset", charset);
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
////            connection.setRequestProperty("User-Agent", USER_AGENT);
//            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
////            wr.writeBytes(messageBody);
//            wr.flush();
//            wr.close();
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            String str = response.toString();
//
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder;
//            InputSource is;
//            is = new InputSource(new StringReader(str));
//            builder = factory.newDocumentBuilder();
//            org.w3c.dom.Document doc = builder.parse(is);

            File fXmlFile = new File("GetAllStationRain.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Row");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String stationname = eElement.getElementsByTagName("StationName").item(0).getTextContent();
                    String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                    String lon = eElement.getElementsByTagName("Longitude").item(0).getTextContent();
                    String lat = eElement.getElementsByTagName("Latitude").item(0).getTextContent();
                    String tenMin = eElement.getElementsByTagName("tenMinute").item(0).getTextContent();
                    String oneHr = eElement.getElementsByTagName("oneHour").item(0).getTextContent();
                    String threeHr = eElement.getElementsByTagName("threeHour").item(0).getTextContent();
                    String sixHr = eElement.getElementsByTagName("sixHour").item(0).getTextContent();
                    String oneDay = eElement.getElementsByTagName("oneDay").item(0).getTextContent();
                    String twentyfourHr = eElement.getElementsByTagName("_24Hour").item(0).getTextContent();
                    String rtime = eElement.getElementsByTagName("rtime").item(0).getTextContent();
                    String twelveHr = eElement.getElementsByTagName("_12Hour").item(0).getTextContent();

                    if (DBManager.getInstance().ifReadingRedundant("swcb_reading", stationname, rtime) == -1) {
                        DBManager.getInstance().insertRain_swcb(stationname, name, lon, lat, tenMin, oneHr, threeHr, sixHr, oneDay, twentyfourHr, rtime, twelveHr);
                        rtime = rtime.replace('/', '-');
                        if (rtime.contains("上午")) {
                            rtime = rtime.replace(" 上午 ", "T") + "+08:00";
                            if (rtime.split("T")[1].startsWith("12")) {
                                String rtime1 = rtime.split("T")[0] + "T";
                                String rtime2 = rtime.split("T")[1].split(":")[1] + ":" + rtime.split("T")[1].split(":")[2] + ":" + rtime.split("T")[1].split(":")[3];
                                String replace = rtime.split("T")[1].split(":")[0].replace("12", "00");
                                rtime = rtime1 + replace + ":" + rtime2;
                            }
                            TimeZone utc = TimeZone.getTimeZone("UTC");
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+08:00");
                            Date date = sdf.parse(rtime);
                            sdf.setTimeZone(utc);
                            System.out.println(sdf.format(date).toString());
                        } else {
                            rtime = rtime.replace(" 下午 ", "T") + "+08:00";
                            if (!rtime.split("T")[1].startsWith("12")) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+08:00");
                                Date date = sdf.parse(rtime);
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(date);
                                cal.add(Calendar.HOUR, +12);
                                date = cal.getTime();
                                TimeZone utc = TimeZone.getTimeZone("UTC");
                                sdf.setTimeZone(utc);
                                System.out.println(sdf.format(date).toString());

                            } else {
                                TimeZone utc = TimeZone.getTimeZone("UTC");
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+08:00");
                                Date date = sdf.parse(rtime);
                                sdf.setTimeZone(utc);
                                System.out.println(sdf.format(date).toString());
                            }

                        }
                        sendInsertobservationRequest(swcburl, stationname, lon, lat, rtime, tenMin, "urn:ogc:def:phenomenon:OGC:2.0:rainfall_10min", stationname);
                        sendInsertobservationRequest(swcburl, stationname, lon, lat, rtime, oneHr, "urn:ogc:def:phenomenon:OGC:2.0:rainfall_1hr", stationname);
                        sendInsertobservationRequest(swcburl, stationname, lon, lat, rtime, threeHr, "urn:ogc:def:phenomenon:OGC:2.0:rainfall_3hr", stationname);
                        sendInsertobservationRequest(swcburl, stationname, lon, lat, rtime, sixHr, "urn:ogc:def:phenomenon:OGC:2.0:rainfall_6hr", stationname);
                        sendInsertobservationRequest(swcburl, stationname, lon, lat, rtime, oneDay, "urn:ogc:def:phenomenon:OGC:2.0:rainfall_1day", stationname);
                        sendInsertobservationRequest(swcburl, stationname, lon, lat, rtime, twentyfourHr, "urn:ogc:def:phenomenon:OGC:2.0:rainfall_24hr", stationname);
                        sendInsertobservationRequest(swcburl, stationname, lon, lat, rtime, twelveHr, "urn:ogc:def:phenomenon:OGC:2.0:rainfall_12hr", stationname);
                    }
                }
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendInsertsensorRequest(String stationname, String url, String onedayornow) {
        try {
            String insertobservationxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
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
                    + "                                    <sml:value>sensor" + stationname + "</sml:value>\n"
                    + "                                </sml:Term>\n"
                    + "                            </sml:identifier>\n"
                    + "                        </sml:IdentifierList>\n"
                    + "                    </sml:identification>\n"
                    + "                    <sml:capabilities name=\"offerings\">\n"
                    + "                        <swe:SimpleDataRecord>\n"
                    + "                            <!-- Field name or gml:name is used for the offering's name -->\n"
                    + "                            <swe:field name=\"" + stationname + "\">\n"
                    + "                                <swe:Text definition=\"urn:ogc:def:identifier:OGC:offeringID\">\n"
                    + "                                    <gml:name>" + stationname + "</gml:name>\n"
                    + "                                    <swe:value>" + stationname + "</swe:value>\n"
                    + "                                </swe:Text>\n"
                    + "                            </swe:field>\n"
                    + "                        </swe:SimpleDataRecord>\n"
                    + "                    </sml:capabilities>\n"
                    + "                </sml:System>\n"
                    + "            </sml:member>\n"
                    + "        </sml:SensorML>\n"
                    + "    </swes:procedureDescription>\n"
                    + "    <!-- multiple values possible -->\n"
                    + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:rainfall_10min</swes:observableProperty>\n"
                    + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:rainfall_1hr</swes:observableProperty>\n"
                    + "  <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:rainfall_3hr</swes:observableProperty>\n"
                    + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:rainfall_6hr</swes:observableProperty>\n"
                    + "  <swes:observableProperty>"+onedayornow+"</swes:observableProperty>\n"
                    + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:rainfall_24hr</swes:observableProperty>\n"
                    + "  <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:rainfall_12hr</swes:observableProperty>\n"
                    + "    <swes:metadata>\n"
                    + "        <sos:SosInsertionMetadata>\n"
                    + "            <sos:observationType>http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement</sos:observationType>\n"
                    + "            <!-- multiple values possible -->\n"
                    + "           \n"
                    + "        </sos:SosInsertionMetadata>\n"
                    + "    </swes:metadata>\n"
                    + "</swes:InsertSensor>";

//            String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
//            URLConnection connection = new URL(url).openConnection();
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/xml");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
//            connection.setRequestProperty("User-Agent", USER_AGENT);

//            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//            wr.writeBytes(insertobservationxml);
            wr.write(insertobservationxml);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String str = response.toString();
            Document doc = Jsoup.parse(str);
            System.out.println("sendInsertsensorRequest\n" + doc);

        } catch (MalformedURLException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendInsertobservationRequest(String url, String stationname, String lon, String lat, String rtime, String reading, String property, String codespace) {
        try {
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
                    + "    <sos:offering>" + stationname + "</sos:offering>\n"
                    + "    <sos:observation>\n"
                    + "        <om:OM_Observation gml:id=\"o1\">\n"
                    + "            <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement\"/>\n"
                    + "            <om:phenomenonTime>\n"
                    + "                <gml:TimeInstant gml:id=\"phenomenonTime\">\n"
                    + "                    <gml:timePosition>" + rtime + "</gml:timePosition>\n"
                    + "                </gml:TimeInstant>\n"
                    + "            </om:phenomenonTime>\n"
                    + "            <om:resultTime xlink:href=\"#phenomenonTime\"/>\n"
                    + "            <om:procedure xlink:href=\"sensor" + stationname + "\"/>\n"
                    + "            <om:observedProperty xlink:href=\"" + property + "\"/>\n"
                    + "            <om:featureOfInterest>\n"
                    + "                <sams:SF_SpatialSamplingFeature gml:id=\"SF_SpatialSamplingFeature\">\n"
                    + "                    <gml:identifier codeSpace=\"\">" + codespace + "</gml:identifier>\n"
                    + "                    <gml:name>52°North</gml:name>\n"
                    + "                    <sf:type xlink:href=\"http://www.opengis.net/def/samplingFeatureType/OGC-OM/2.0/SF_SamplingPoint\"/>\n"
                    + "                    <sf:sampledFeature xlink:href=\"http://www.52north.org/test/featureOfInterest/1\"/>\n"
                    + "                    <sams:shape>\n"
                    + "                        <gml:Point gml:id=\"test_feature_9\">\n"
                    + "                            <gml:pos srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\">" + lon + " " + lat + "</gml:pos>\n"
                    + "                        </gml:Point>\n"
                    + "                    </sams:shape>\n"
                    + "                </sams:SF_SpatialSamplingFeature>\n"
                    + "            </om:featureOfInterest>\n"
                    + "            <om:result xsi:type=\"gml:MeasureType\" uom=\"mm\">" + reading + "</om:result>\n"
                    + "        </om:OM_Observation>\n"
                    + "    </sos:observation>\n"
                    + "</sos:InsertObservation>";

//            String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
//            URLConnection connection = new URL(url).openConnection();
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/xml");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
//            connection.setRequestProperty("User-Agent", USER_AGENT);

//            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//            wr.writeBytes(insertobservationxml);
            wr.write(insertobservationxml);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String str = response.toString();
            Document doc = Jsoup.parse(str);
            System.out.println("sendInsertObservationRequest\n" + doc);

        } catch (MalformedURLException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getrainFromEPA() {
        try {
            String epaurl = "http://localhost:8080/epa-sos/service";
            String url = "http://opendata.epa.gov.tw/ws/Data/RainTenMin/?$orderby=PublishTime%20desc&$skip=0&$top=1000&format=xml";
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String str = response.toString();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            InputSource is;
            is = new InputSource(new StringReader(str));
            builder = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = builder.parse(is);
            NodeList nList = doc.getElementsByTagName("Data");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String SiteId = eElement.getElementsByTagName("SiteId").item(0).getTextContent();
                    String SiteName = eElement.getElementsByTagName("SiteName").item(0).getTextContent();
                    String County = eElement.getElementsByTagName("County").item(0).getTextContent();
                    String Township = eElement.getElementsByTagName("Township").item(0).getTextContent();
                    String TWD67Lon = eElement.getElementsByTagName("TWD67Lon").item(0).getTextContent();
                    String TWD67Lat = eElement.getElementsByTagName("TWD67Lat").item(0).getTextContent();
                    String Rainfall10min = eElement.getElementsByTagName("Rainfall10min").item(0).getTextContent();
                    String Rainfall1hr = eElement.getElementsByTagName("Rainfall1hr").item(0).getTextContent();
                    String Rainfall3hr = eElement.getElementsByTagName("Rainfall3hr").item(0).getTextContent();
                    String Rainfall6hr = eElement.getElementsByTagName("Rainfall6hr").item(0).getTextContent();
                    String Rainfall12hr = eElement.getElementsByTagName("Rainfall12hr").item(0).getTextContent();
                    String Rainfall24hr = eElement.getElementsByTagName("Rainfall24hr").item(0).getTextContent();
                    String Now = eElement.getElementsByTagName("Now").item(0).getTextContent();
                    String Unit = eElement.getElementsByTagName("Unit").item(0).getTextContent();
                    String PublishTime = eElement.getElementsByTagName("PublishTime").item(0).getTextContent();
                    PublishTime = PublishTime.replace(" ", "T");
//                    float tenmin = Float.parseFloat(Rainfall10min);
//                    float onehr = Float.parseFloat(Rainfall1hr);
//                    float threehr = Float.parseFloat(Rainfall3hr);
//                    float sixhr = Float.parseFloat(Rainfall6hr);
//                    float twelvehr = Float.parseFloat(Rainfall12hr);
//                    float twentyfourhr = Float.parseFloat(Rainfall24hr);
                    if (DBManager.getInstance().ifStationRedundant("epa_station", SiteId) == -1) {
                        DBManager.getInstance().insertStation_epa(SiteId, SiteName, County, Township, Unit);
                        sendInsertsensorRequest(SiteName,epaurl,"urn:ogc:def:phenomenon:OGC:2.0:rainfall_now");
                    }
                    if (DBManager.getInstance().ifReadingRedundant("epa_reading", SiteId, PublishTime) == -1) {
                        DBManager.getInstance().insertRain_epa(SiteName, TWD67Lon, TWD67Lat, Rainfall10min, Rainfall1hr, Rainfall3hr, Rainfall6hr, Now, Rainfall24hr, PublishTime, Rainfall12hr);
                        sendInsertobservationRequest(epaurl, SiteName, TWD67Lon, TWD67Lat, PublishTime, Rainfall10min, "urn:ogc:def:phenomenon:OGC:2.0:rainfall_10min", SiteId);
                        sendInsertobservationRequest(epaurl, SiteName, TWD67Lon, TWD67Lat, PublishTime, Rainfall1hr, "urn:ogc:def:phenomenon:OGC:2.0:rainfall_1hr", SiteId);
                        sendInsertobservationRequest(epaurl, SiteName, TWD67Lon, TWD67Lat, PublishTime, Rainfall3hr, "urn:ogc:def:phenomenon:OGC:2.0:rainfall_3hr", SiteId);
                        sendInsertobservationRequest(epaurl, SiteName, TWD67Lon, TWD67Lat, PublishTime, Rainfall6hr, "urn:ogc:def:phenomenon:OGC:2.0:rainfall_6hr", SiteId);
                        sendInsertobservationRequest(epaurl, SiteName, TWD67Lon, TWD67Lat, PublishTime, Rainfall12hr, "urn:ogc:def:phenomenon:OGC:2.0:rainfall_12hr", SiteId);
                        sendInsertobservationRequest(epaurl, SiteName, TWD67Lon, TWD67Lat, PublishTime, Rainfall24hr, "urn:ogc:def:phenomenon:OGC:2.0:rainfall_24hr", SiteId);
                        sendInsertobservationRequest(epaurl, SiteName, TWD67Lon, TWD67Lat, PublishTime, Now, "urn:ogc:def:phenomenon:OGC:2.0:rainfall_now", SiteId);
                    }
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

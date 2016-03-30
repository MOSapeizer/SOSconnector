/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sosconnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.*;
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

/**
 *
 * @author Hank
 * @refactor Mos
 */
public class EPA_AQX extends TimerTask {
    
    private final static String USER_AGENT = "Mozilla/5.0";

    
    @Override
    public void run() {
        getAQXStationFromEPA();
        getAQXReadingFromEPA();
    }

    public void writeToDocumnet(String input) {
        Document doc = Jsoup.parse( input );
        System.out.println("sendInsertsensorRequest\n" + doc);
    }

    public String sendInsertRequest(String url, String insertString) {
        InsertRequest request = null;
        try {
            request = new InsertRequest(url);
            request.writeIn( insertString );
            return request.getResponseBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendInsertsensorRequest(String stationName, String url) {
        String insertSensorXml = InsertRequest.insertSensorXml(stationName);
        String response = sendInsertRequest(url, insertSensorXml);
        writeToDocumnet( response );
    }
    
    public void sendInsertobservationRequest(String url, String stationName, StringBuffer allObsString) {
        String insertSensorXml = InsertRequest.insertObservationXml(stationName, allObsString);
        String response = sendInsertRequest(url, insertSensorXml);
        writeToDocumnet( response );
    }
    
    private void getAQXStationFromEPA() {
        try {
            String epaurl = "http://localhost:8080/epa-aqx-sos/service";
            String url = "http://opendata.epa.gov.tw/ws/Data/AQXSite/?$orderby=SiteName&$skip=0&$top=1000&format=xml";
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
                    String SiteName = eElement.getElementsByTagName("SiteName").item(0).getTextContent();
                    String SiteEngName = eElement.getElementsByTagName("SiteEngName").item(0).getTextContent();
                    String AreaName = eElement.getElementsByTagName("AreaName").item(0).getTextContent();
                    String County = eElement.getElementsByTagName("County").item(0).getTextContent();
                    String Township = eElement.getElementsByTagName("Township").item(0).getTextContent();
                    String SiteAddress = eElement.getElementsByTagName("SiteAddress").item(0).getTextContent();
                    String TWD97Lon = eElement.getElementsByTagName("TWD97Lon").item(0).getTextContent();
                    String TWD97Lat = eElement.getElementsByTagName("TWD97Lat").item(0).getTextContent();
                    String SiteType = eElement.getElementsByTagName("SiteType").item(0).getTextContent();
                    
                
                    if (DBManager.getInstance().ifStationRedundant("epa_aqx_station", SiteName) == -1) {
                        DBManager.getInstance().insertStation_epa_aqx(SiteName, SiteEngName, AreaName, County, Township, SiteAddress, TWD97Lon, TWD97Lat, SiteType);
                        sendInsertsensorRequest(SiteName,epaurl);
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
        
    private void getAQXReadingFromEPA() {
        try {
            String epaurl = "http://localhost:8080/epa-aqx-sos/service";
            String url = "http://opendata.epa.gov.tw/ws/Data/AQX/?$orderby=SiteName&$skip=0&$top=1000&format=xml";
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
                    String SiteName = eElement.getElementsByTagName("SiteName").item(0).getTextContent();
                    String County = eElement.getElementsByTagName("County").item(0).getTextContent();
                    String PSI = eElement.getElementsByTagName("PSI").item(0).getTextContent();
                    String MajorPollutant = eElement.getElementsByTagName("MajorPollutant").item(0).getTextContent();
                    String Status = eElement.getElementsByTagName("Status").item(0).getTextContent();
                    String SO2 = eElement.getElementsByTagName("SO2").item(0).getTextContent();
                    String CO = eElement.getElementsByTagName("CO").item(0).getTextContent();
                    String O3 = eElement.getElementsByTagName("O3").item(0).getTextContent();
                    String PM10 = eElement.getElementsByTagName("PM10").item(0).getTextContent();
                    String PM2_5 = eElement.getElementsByTagName("PM2.5").item(0).getTextContent();
                    String NO2 = eElement.getElementsByTagName("NO2").item(0).getTextContent();
                    String WindSpeed = eElement.getElementsByTagName("WindSpeed").item(0).getTextContent();
                    String WindDirec = eElement.getElementsByTagName("WindDirec").item(0).getTextContent();
                    String FPMI = eElement.getElementsByTagName("FPMI").item(0).getTextContent();
                    String NOx = eElement.getElementsByTagName("NOx").item(0).getTextContent();
                    String NO = eElement.getElementsByTagName("NO").item(0).getTextContent();
                    String PublishTime = eElement.getElementsByTagName("PublishTime").item(0).getTextContent();
                    PublishTime = PublishTime.replace(" ", "T");
                    
                    if (DBManager.getInstance().ifReadingRedundant("epa_aqx_reading", SiteName, PublishTime) == -1) {
                        DBManager.getInstance().insertAQX_epa(SiteName, County, PSI, MajorPollutant, Status, SO2, CO, O3, PM10, PM2_5, NO2,WindSpeed ,WindDirec ,FPMI ,NOx ,NO , PublishTime);
                        String TWD97Lon = DBManager.getInstance().Getlon(SiteName);
                        String TWD97Lat = DBManager.getInstance().Getlat(SiteName);
            
                        StringBuffer allobsstring = new StringBuffer();
                        allobsstring = allobsstring.append(composestring("1",PublishTime, SiteName, "urn:ogc:def:phenomenon:OGC:2.0:AQX_PSI", SiteName, TWD97Lon, TWD97Lat, PSI));
                        allobsstring = allobsstring.append(composestring("2",PublishTime, SiteName, "urn:ogc:def:phenomenon:OGC:2.0:AQX_SO2", SiteName, TWD97Lon, TWD97Lat, SO2));
                        allobsstring = allobsstring.append(composestring("3",PublishTime, SiteName, "urn:ogc:def:phenomenon:OGC:2.0:AQX_CO", SiteName, TWD97Lon, TWD97Lat, CO));
                        allobsstring = allobsstring.append(composestring("4",PublishTime, SiteName, "urn:ogc:def:phenomenon:OGC:2.0:AQX_O3", SiteName, TWD97Lon, TWD97Lat, O3));
                        allobsstring = allobsstring.append(composestring("5",PublishTime, SiteName, "urn:ogc:def:phenomenon:OGC:2.0:AQX_PM10", SiteName, TWD97Lon, TWD97Lat, PM10));
                        allobsstring = allobsstring.append(composestring("6",PublishTime, SiteName, "urn:ogc:def:phenomenon:OGC:2.0:AQX_PM2.5", SiteName, TWD97Lon, TWD97Lat, PM2_5));
                        allobsstring = allobsstring.append(composestring("7",PublishTime, SiteName, "urn:ogc:def:phenomenon:OGC:2.0:AQX_NO2", SiteName, TWD97Lon, TWD97Lat, NO2));
                        allobsstring = allobsstring.append(composestring("8",PublishTime, SiteName, "urn:ogc:def:phenomenon:OGC:2.0:AQX_WindSpeed", SiteName, TWD97Lon, TWD97Lat, WindSpeed));
                        allobsstring = allobsstring.append(composestring("9",PublishTime, SiteName, "urn:ogc:def:phenomenon:OGC:2.0:AQX_WindDirec", SiteName, TWD97Lon, TWD97Lat, WindDirec));
                        allobsstring = allobsstring.append(composestring("10",PublishTime, SiteName, "urn:ogc:def:phenomenon:OGC:2.0:AQX_FPMI", SiteName, TWD97Lon, TWD97Lat, FPMI));
                        allobsstring = allobsstring.append(composestring("11",PublishTime, SiteName, "urn:ogc:def:phenomenon:OGC:2.0:AQX_NOx", SiteName, TWD97Lon, TWD97Lat, NOx));
                        allobsstring = allobsstring.append(composestring("12",PublishTime, SiteName, "urn:ogc:def:phenomenon:OGC:2.0:AQX_NO", SiteName, TWD97Lon, TWD97Lat, NO));
                        
                        sendInsertobservationRequest(epaurl, SiteName, allobsstring);
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
    
    private String composestring(String oid, String rtime, String stationname, String property, String codespace, String lon, String lat, String reading){
        String obsstring = "<sos:observation>\n"
                    + "        <om:OM_Observation gml:id=\"o" + oid + "\">\n"
                    + "            <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement\"/>\n"
                    + "            <om:phenomenonTime>\n"
                    + "                <gml:TimeInstant gml:id=\"phenomenonTime" + oid + "\">\n"
                    + "                    <gml:timePosition>" + rtime + "</gml:timePosition>\n"
                    + "                </gml:TimeInstant>\n"
                    + "            </om:phenomenonTime>\n"
                    + "            <om:resultTime xlink:href=\"#phenomenonTime" + oid + "\"/>\n"
                    + "            <om:procedure xlink:href=\"urn:ogc:object:feature:Sensor:EPA:sensor" + stationname + "\"/>\n"
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
                    + "    </sos:observation>\n";
        
        return obsstring;
    }
}
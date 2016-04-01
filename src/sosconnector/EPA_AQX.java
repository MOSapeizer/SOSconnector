/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sosconnector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hank
 * @refactor Mos
 */
public class EPA_AQX extends TimerTask {
    
    private final static String USER_AGENT = "Mozilla/5.0";
    private final String epaURL = "http://localhost:8080/epa-aqx-sos/service";
    private final String SiteURL = "http://opendata.epa.gov.tw/ws/Data/AQXSite/?$orderby=SiteName&$skip=0&$top=1000&format=xml";
    private final String opendataURL = "http://opendata.epa.gov.tw/ws/Data/AQX/?$orderby=SiteName&$skip=0&$top=1000&format=xml";
    @Override
    public void run() {
        getAQXStationFromEPA();
        getAQXReadingFromEPA();
    }


    private void getAQXStationFromEPA() {
        try {
            Request request = new Request( SiteURL );
            request.setConnection("GET");
            String response = request.getResponseBody();
            insertSiteIntoDatabase(response);
        } catch (IOException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getAQXReadingFromEPA() {
        try {

            Request request = new Request( opendataURL );
            request.setConnection("GET");
            String response = request.getResponseBody();
            insertObservationIntoDatabase( response );

        } catch (IOException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public void sendInsertsensorRequest(String stationName, String url) {
        String insertSensorXml = InsertRequest.getInsertSensorXml(stationName);
        String response = sendInsertRequest(url, insertSensorXml);
        writeToDocumnet( response );
    }
    
    public void sendInsertobservationRequest(String url, String stationName, StringBuffer allObsString) {
        String insertObservationXml = InsertRequest.getInsertObservationXml(stationName, allObsString);
        String response = sendInsertRequest(url, insertObservationXml);
        writeToDocumnet( response );
    }

    public DocumentBuilder getDOMBuilder() throws ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    protected org.w3c.dom.Document parseSource(String source) {
        DocumentBuilder builder = null;
        try {
            builder = getDOMBuilder();
            InputSource is = new InputSource( new StringReader(source) );
            return builder.parse(is);
        } catch (ParserConfigurationException e) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, e);
        } catch (SAXException e) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public NodeList getDataListFrom(String source){
        org.w3c.dom.Document dom = parseSource(source);
        return dom.getElementsByTagName("Data");
    }

    public void sendInsertRequestIfNotRedundant(SiteDTO site){
        if(site == null) return;
        String siteName = site.getSiteName();
        if (DBManager.getInstance().ifStationRedundant("epa_aqx_station", siteName) == -1) {
            DBManager.getInstance().insertStation_epa_aqx(site);
            sendInsertsensorRequest(siteName, epaURL);
        }
    }

    public SiteDTO makeSite (Node node){
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            return new SiteDTO((Element) node);
        }
        return null;
    }

    public ObservationDTO makeObservation (Node node){
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            return new ObservationDTO( (Element) node );
        }
        return null;
    }

    public void insertObservationIntoDatabase(String str){
        NodeList list = getDataListFrom(str);
        for (int temp = 0; temp < list.getLength(); temp++) {
            Node node = list.item(temp);
            ObservationDTO obs = makeObservation(node);
            if(obs != null) sendInsertObservationRequestIfNotRedundant(obs);
        }
    }

    public void insertSiteIntoDatabase(String str){
        NodeList list = getDataListFrom(str);
        for (int index = 0; index < list.getLength(); index++) {
            Node node = list.item(index);
            SiteDTO site = makeSite(node);
            if(site != null) sendInsertRequestIfNotRedundant(site);
        }
    }
    


    public String[] latLon(String siteName){
        String[] position = new String[2];
        position[0] = DBManager.getInstance().Getlon( siteName );
        position[1] = DBManager.getInstance().Getlat( siteName );
        return position;
    }

    public StringBuffer allObsInSite(ObservationDTO obs){
        String[] latLon = latLon( obs.getSiteName() );
        StringBuffer allObsString = new StringBuffer();
        allObsString = allObsString.append( obs.observationXML("1", latLon, "PSI", obs.getPSI() ) );
        allObsString = allObsString.append(obs.observationXML("2", latLon, "SO2", obs.getSO2() ));
        allObsString = allObsString.append(obs.observationXML("3", latLon, "CO", obs.getCO() ));
        allObsString = allObsString.append(obs.observationXML("4", latLon, "O3", obs.getO3() ));
        allObsString = allObsString.append(obs.observationXML("5", latLon, "PM10", obs.getPM10() ));
        allObsString = allObsString.append(obs.observationXML("6", latLon, "PM2.5", obs.getPM2_5() ));
        allObsString = allObsString.append(obs.observationXML("7", latLon, "NO2", obs.getNO2() ));
        allObsString = allObsString.append(obs.observationXML("8", latLon, "WindSpeed", obs.getWindSpeed() ));
        allObsString = allObsString.append(obs.observationXML("9", latLon, "WindDirec", obs.getWindDirec() ));
        allObsString = allObsString.append(obs.observationXML("10", latLon, "FPMI", obs.getFPMI() ));
        allObsString = allObsString.append(obs.observationXML("11", latLon, "NOx", obs.getNOx() ));
        allObsString = allObsString.append(obs.observationXML("12", latLon, "NO", obs.getNO() ));
        return allObsString;
    }

    public void sendInsertObservationRequestIfNotRedundant(ObservationDTO obs){
        String siteName = obs.getSiteName();
        String publishTime = obs.getPublishTime();
        if (DBManager.getInstance().ifReadingRedundant("epa_aqx_reading", siteName, publishTime) == -1) {
            DBManager.getInstance().insertAQX_epa( obs );
            sendInsertobservationRequest(epaURL, siteName, allObsInSite(obs));
        }
    }

}

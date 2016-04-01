package sosconnector.Factory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sosconnector.DomParser;
import sosconnector.Request.InsertRequest;
import sosconnector.SOSConnector;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/4/1.
 */
abstract public class ResponseFactory<T> {

    protected String source;
    protected final String epaURL = "http://localhost:8080/epa-aqx-sos/service";

    public ResponseFactory(String source){
        this.source = source;
    }

    public void start(){
        insertDataIntoDatabase();
    }

    public void insertDataIntoDatabase(){
        NodeList list = new DomParser( source ).getDataList();
        for (int temp = 0; temp < list.getLength(); temp++) {
            Node node = list.item(temp);
            T obj = make(node);
            if(obj != null) manipulateObjIfNotRedundant(obj);
        }
    }

    public void manipulateObjIfNotRedundant(T obj){
        if ( whichIsNotRedundant( obj ) ) finalManipulate( obj );
    }

    public void sendInsertRequestWithPayload(String stationName, String url) {
        String insertSensorXml = getXML( stationName );
        String response = sendInsertRequest(url, insertSensorXml);
        writeToDocumnet( response );
    }

    abstract public Boolean whichIsNotRedundant(T obj);

    abstract public T make(Node node);

    abstract public void finalManipulate(T obj);

    abstract public String getXML( String name );

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

    public void writeToDocumnet(String input) {
        Document doc = Jsoup.parse( input );
        System.out.println("sendInsertsensorRequest\n" + doc);
    }

}

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
    protected String dataName;
    protected final String epaURL = "http://localhost:8080/epa-aqx-sos/service";

    public ResponseFactory(String source){
        this.source = source;
    }

    public void setDataName(String dataName){
        this.dataName = dataName;
    }

    public void start(){
        insertDataIntoDatabase();
    }

    public void insertDataIntoDatabase(){
        initialize();
        NodeList list = new DomParser( source ).getDataList(dataName);
        for (int temp = 0; temp < list.getLength(); temp++) {
            Node node = list.item(temp);
            T obj = operateNode(node);
            if(obj != null) manipulateObjIfNotRedundant(obj);
        }
    }

    public void manipulateObjIfNotRedundant(T obj){
        if ( whichIsRedundant( obj ) )
            return;
        else finalManipulate( obj );
    }

    public void sendInsertRequestWithPayload(String stationName, String url) {
        String insertSensorXml = getXML( stationName );
        String response = sendInsertRequest(url, insertSensorXml);
        writeToDocumnet( response );
    }

    abstract public Boolean whichIsRedundant(T obj);
    abstract public void initialize();
    abstract public T operateNode(Node node);

    abstract public void finalManipulate(T obj);

    abstract public String getXML( String name );

    public String sendInsertRequest(String url, String requestBody) {
        InsertRequest request = null;
        try {
            request = new InsertRequest(url);
            request.writeIn( requestBody );
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

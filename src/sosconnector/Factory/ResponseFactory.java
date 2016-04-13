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

    public void setFilterNodeName(String dataName){
        this.dataName = dataName;
    }

    public void start(){
        insertDataIntoDatabase();
    }

    public void insertDataIntoDatabase(){
        initialize();
        NodeList list = parseXMLtoList();
        filterAndManipulateNodesIn( list );
    }

    private NodeList parseXMLtoList() {
        return new DomParser( source ).getDataList(dataName);
    }

    private void filterAndManipulateNodesIn(NodeList list) {
        for (int index = 0; index < list.getLength(); index++) {
            Node node = list.item(index);
            T obj = operateFilteredNode(node);
            if(obj != null) manipulateObjIfNotRedundant(obj);
        }
    }

    private void manipulateObjIfNotRedundant(T obj){
        if (!whichFilteredNodeIsRedundant( obj )) {
            finalManipulate( obj );
        }
    }

    protected String sendInsertRequest(String url, String requestBody) {
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

    protected void writeToDocumnet(String input) {
        Document doc = Jsoup.parse( input );
        System.out.println("sendInsertsensorRequest\n" + doc);
    }
    abstract public void initialize();
    abstract public T operateFilteredNode(Node node);
    abstract public Boolean whichFilteredNodeIsRedundant(T obj);
    abstract public void finalManipulate(T obj);

}

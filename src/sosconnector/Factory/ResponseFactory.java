package sosconnector.Factory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
    protected String url = setSOSUrl();

    public ResponseFactory(String source){
        this.source = source;
    }

    public void setFilterNodeName(String dataName){
        this.dataName = dataName;
    }

    public void work(){
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
            //這邊應該可以把node都存起來
            //然後把manipulate的時機放在迴圈做完的時候
            if(obj != null) manipulateObjIfNotRedundant(obj);
        }
        //就是這個位置來manipulate 推好的DTO，存完錢開始撒錢的感覺？
    }

    private void manipulateObjIfNotRedundant(T obj){
        if (!whichFilteredNodeIsRedundant( obj )) {
            finalManipulate( obj );
        }
    }

    protected String sendInsertRequest(String requestBody) {
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

    protected abstract String setSOSUrl();
    abstract public void initialize();
    abstract public T operateFilteredNode(Node node);
    abstract public Boolean whichFilteredNodeIsRedundant(T obj);
    abstract public void finalManipulate(T obj);

}

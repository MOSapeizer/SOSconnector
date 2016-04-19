package sosconnector.DAO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sosconnector.Factory.DomParser;
import sosconnector.Request.Request;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/4/1.
 */
abstract public class DAOFactory<T> {

    private String source;
    private String dataName;
    private String url;
    private LinkedList<T> list;

    public DAOFactory(String url){
        this.url = url;
        this.source = getSourceFormGOV();
        work();
    }

    public String getInsertSensorXML(){
        return null;
    }

    public String getInsertObservationXML(){
        return null;
    }

    public void work(){
        initialize();
        NodeList list = parseXMLtoNodeList();
        filter( list );
    }

    private String getSourceFormGOV(){
        try {
            return new Request("GET", url).getResponseBody();
        } catch (IOException e) {
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, "Can't get Resource From gov URL.", e);
        }
        return null;
    }

    void setFilterNodeName(String dataName){
        this.dataName = dataName;
    }

    private NodeList parseXMLtoNodeList() {
        return new DomParser( source ).getDataList(dataName);
    }

    private void filter(NodeList list) {
        for (int index = 0; index < list.getLength(); index++) {
            Node node = list.item(index);
            T obj = map(node);
            if(obj != null) reduce(obj);
        }
    }

    abstract public void initialize();

    abstract public T map(Node node);

    private void reduce(T obj){
        if (!condition( obj )) {
            output( obj );
        }
    }

    protected void writeToDocumnet(String input) {
        Document doc = Jsoup.parse( input );
        System.out.println("sendInsertsensorRequest\n" + doc);
    }

    abstract public Boolean condition(T obj);
    abstract public void output(T obj);

}

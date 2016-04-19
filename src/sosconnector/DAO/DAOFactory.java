package sosconnector.DAO;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sosconnector.DTO.DTOFactory;
import sosconnector.Factory.DomParser;
import sosconnector.Request.Request;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/4/1.
 */

//必須重構到只剩下getInsertSensorXML還有getInsertObservationXML就好
//    參數的部分只需要考慮URL和設定檔的黨名。
abstract public class DAOFactory {

    private String dataName;
    private DTOFactory dto;
    private String url;

    public DAOFactory(String url, String configure_path){
        this.url = url;
        this.dto = new DTOFactory(new File(configure_path),  getSourceFormGOV() );
//        work();
    }

    public String getInsertSensorXML(){
        return null;
    }

    public String getInsertObservationXML(){
        return null;
    }

//    public void work(){
//        NodeList list = parseXMLtoNodeList();
//        filter( list );
//    }

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

//    private NodeList parseXMLtoNodeList() {
//        return new DomParser( source ).getDataList(dataName);
//    }
//
//    private void filter(NodeList list) {
//        for (int index = 0; index < list.getLength(); index++) {
//            Node node = list.item(index);
//            T obj = map(node);
//            if(obj != null) reduce(obj);
//        }
//    }


}

package sosconnector.Parser;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import sosconnector.Configure.Configure;
import sosconnector.Configure.Info;
import sosconnector.Configure.InsertObservation;
import sosconnector.Configure.InsertSensor;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by zil on 2016/4/19.
 */
public class NodeParser {

    private NodeList list;
    private Info info;
    private InsertSensor insertSensor;
    private InsertObservation insertObservation;

    public NodeParser(DomParser dom, Configure configure){
        this.insertSensor = configure.getInsertSensor();
        this.info = configure.getInfo();
        this.insertObservation = configure.getInsertObservation();
        this.list = dom.getDataList(info.getXmlDataRoot());
    }

    public LinkedList<LinkedHashMap> parse(){
        LinkedList<LinkedHashMap> data = new LinkedList<>();
        collect(data);
        return data;
    }

    private void collect(LinkedList<LinkedHashMap> data){
        if(list != null)
            for (int index = 0; index < list.getLength(); index++) {
                Element node = (Element) list.item(index);
                LinkedHashMap match = match(node);
                data.push(match);
            }
    }

    private LinkedHashMap match(Element node){
        return new ParserAdapter(node).parse(insertObservation.getTimestamp(), insertSensor.getOffering(), insertObservation.getObservation());
    }

}

package sosconnector.Parser;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import sosconnector.GovConfigure.Configure;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by zil on 2016/4/19.
 */
public class NodeParser {

    private NodeList list;
    private Configure configure;
    private ParserAdapter parser;

    public NodeParser(DomParser dom, Configure configure){
        this.configure = configure;
        this.list = dom.getDataList( configure.getRoot() );
    }

    public LinkedList<LinkedHashMap> parse(){
        LinkedList<LinkedHashMap> data = new LinkedList<>();
        collect(data);
        return data;
    }

    private void collect(LinkedList<LinkedHashMap> data){
        for (int index = 0; index < list.getLength(); index++) {
            Element node = (Element) list.item(index);
            LinkedList<LinkedHashMap> match = match(node);
            data.addAll(match);
        }
    }

    private LinkedList<LinkedHashMap> match(Element node){
        LinkedList<LinkedHashMap> objects  = parser.parse(configure.getChild(), node);
        return objects;
    }

}

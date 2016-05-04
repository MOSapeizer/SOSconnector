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
        if(list != null)
            for (int index = 0; index < list.getLength(); index++) {
                Element node = (Element) list.item(index);
                LinkedHashMap match = match(node);
                data.push(match);
            }
    }

    private LinkedHashMap match(Element node){
        LinkedHashMap objects  = new ParserAdapter(node).parse(configure.getChild());
        return objects;
    }

}

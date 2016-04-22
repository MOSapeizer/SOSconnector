package sosconnector.Parser;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import sosconnector.Configure;

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
        String type = configure.getType();
        this.parser = new ParserAdapter( ParserAdapter.Type.valueOf(type) );
    }

    public LinkedList<LinkedHashMap> parse(){
        LinkedList<LinkedHashMap> data = new LinkedList<>();
        collect(data);
        return data;
    }

    private void collect(LinkedList<LinkedHashMap> data){
        for (int index = 0; index < list.getLength(); index++) {
            Element node = (Element) list.item(index);
            LinkedHashMap<String, String> hashMap = match(node);
            data.push(hashMap);
        }
    }

    private LinkedHashMap<String, String> match(Element node){
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        for(String child : configure.getChild()){
            parser.parse(hashMap, child, node);
        }
        return hashMap;
    }

}

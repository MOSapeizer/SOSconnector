package sosconnector.Parser;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import sosconnector.Configure;
import sosconnector.Factory.DomParser;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by zil on 2016/4/19.
 */
public class NodeParser {

    private NodeList list;
    private Configure configure;

    public NodeParser(DomParser dom, Configure configure ){
        this.configure = configure;
        this.list = dom.getDataList( configure.getRoot() );
    }

    public LinkedList<HashMap> parse(){
        LinkedList<HashMap> data = new LinkedList<>();
        collect(data);
        return data;
    }

    private void collect(LinkedList<HashMap> data){
        for (int index = 0; index < list.getLength(); index++) {
            Element node = (Element) list.item(index);
            HashMap<String, String> hashMap = match(node);
            data.push(hashMap);
        }
    }

    private HashMap<String, String> match(Element node){
        HashMap<String, String> hashMap = new HashMap<>();
        for(String child : configure.getChild()){
            hashMap.put(child, getTagContent(node, child));
        }
        return hashMap;
    }

    private String getTagContent(Element node, String tag) {
        return node.getElementsByTagName(tag).item(0).getTextContent();
    }

}

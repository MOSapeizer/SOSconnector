package sosconnector.Parser;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import sosconnector.Configure;
import sosconnector.Factory.DomParser;

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
        for (int index = 0; index < list.getLength(); index++) {
            Element node = (Element) list.item(index);
            LinkedHashMap<String, String> hashMap = match(node);
            data.push(hashMap);
        }
    }

    private LinkedHashMap<String, String> match(Element node){
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        for(String child : configure.getChild()){
            String name = justifyName(child);
            hashMap.put(name, getTagContent(node, child));
        }
        return hashMap;
    }

    private String justifyName(String name){
        String[] split = name.split(":");
        if(split.length >= 2)
            return split[1];
        return split[0];
    }

    private String getTagContent(Element node, String tag) {
        return node.getElementsByTagName(tag).item(0).getTextContent();
    }

}

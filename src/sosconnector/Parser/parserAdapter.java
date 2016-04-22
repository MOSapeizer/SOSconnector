package sosconnector.Parser;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by zil on 2016/4/22.
 */
public class ParserAdapter {

    private Type type;

    public enum Type{
        SINGLE, MULTIPLE, DUPLICATE
    }

    public ParserAdapter(Type type){
        this.type = type;
    }

    public void parse(HashMap<String, String> hashMap,  String child, Element node){
        String name = justifyName(child);
        switch (type) {
            case SINGLE:
                hashMap.put(name, getTagContent(node, child));
                break;
            case MULTIPLE:
                pushDeepTagContent(hashMap, getTags(node, child));
                break;
            case DUPLICATE:

                break;
        }
    }

    private String justifyName(String name){
        String[] split = name.split(":");
        if(split.length >= 2)
            return split[1];
        return split[0];
    }

    private NodeList getTags(Element node, String name){
        return node.getElementsByTagName(name);
    }

    private String getTagContent(Element node, String name) {
        return node.getElementsByTagName(name).item(0).getTextContent();
    }

    private void pushDeepTagContent(HashMap<String, String> hashMap, NodeList node){
        for( int index = 0 ; index < node.getLength() ; index++ ){
            hashMap.put("value" + index, node.item(index).getTextContent());
        }
    }
}

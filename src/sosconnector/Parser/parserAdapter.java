package sosconnector.Parser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sosconnector.GovConfigure.Child;
import java.util.LinkedHashMap;

/**
 * Created by zil on 2016/4/22.
 */

class ParserAdapter {


    private final Element node;

    ParserAdapter(Element node){
        this.node = node;
    }

    LinkedHashMap parse(Child[] tags) {
        LinkedHashMap<String, Object> packet = new LinkedHashMap<>();
        for (Child tag : tags) {
            String tagName =  tag.value;
            if( tag.type == null ){
                packet.put(tagName, getTagContent(tagName));
            } else if (tag.type.equals("DUPLICATE")) {
                putDupliTagsContent(packet, tagName);
            } else if (tag.type.equals("MULTIPLE")) {
                putMultiTagsContent(packet, tagName);
            }
        }
        return packet;
    }

    private String justifyName(String name){
        String[] split = name.split(":");
        if(split.length >= 2)
            return split[1];
        return split[0];
    }

    private void putMultiTagsContent(LinkedHashMap<String, Object> packet , String name){
        Integer id = 1;
        String[] multiTagContent = getMultiTagContent(name);
        for( String tagContent: multiTagContent ){
            packet.put(name + id, tagContent);
            id++;
        }
    }

    private void putDupliTagsContent(LinkedHashMap<String, Object> packet , String name){
        NodeList list = node.getElementsByTagName(name);
        for( int index = 0 ; index < list.getLength() ; index++ ){
            Element item = (Element) list.item(index);
            packet.put(name, item.getTextContent());
        }
    }

    private String getTagContent(String name) {
        return node.getElementsByTagName(name).item(0).getTextContent();
    }

    private String[] getMultiTagContent(String name){
        NodeList list = node.getElementsByTagName(name);
        String[] content = new String[ list.getLength() ];

        for( int index = 0 ; index < list.getLength() ; index++ ){
            Element item = (Element) list.item(index);
            content[index] = item.getTextContent();
        }
        return content;
    }
}

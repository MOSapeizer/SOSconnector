package sosconnector.Parser;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import sosconnector.Configure.Observation;

import java.util.LinkedHashMap;

/**
 * Created by zil on 2016/4/22.
 */

class ParserAdapter {


    private final Element node;

    ParserAdapter(Element node){
        this.node = node;
    }

    LinkedHashMap parse(String timestampTag, String offeringTag, Observation[] observations) {
        LinkedHashMap<String, Object> packet = new LinkedHashMap<>();
        linkTag(packet, "timestamp", timestampTag);
        linkTag(packet, "offering", offeringTag);
        linkObservation(packet, observations);
        return packet;
    }

    private void linkObservation(LinkedHashMap<String, Object> packet, Observation[] observations){
        int index = 0;
        String[] allValue = new String[0];
        for (Observation observation : observations) {
            String tag =  observation.value;
            if( allValue.length == index )
                allValue = getAllValue(tag);
            String value = allValue[index++];
            packet.put(observation.name, value);
        }
    }

    private void linkTag(LinkedHashMap<String, Object> packet, String key, String tagname){
        String timestamp = getAllValue(tagname)[0];
        packet.put(key, timestamp);
    }

    private String[] getAllValue(String name){
        NodeList list = node.getElementsByTagName(name);
        String[] content = new String[ list.getLength() ];

        for( int index = 0 ; index < list.getLength() ; index++ ){
            Element item = (Element) list.item(index);
            content[index] = item.getTextContent();
        }
        return content;
    }
}

package sosconnector.Parser;

import org.w3c.dom.Element;

import java.util.HashMap;

/**
 * Created by zil on 2016/4/19.
 */
public class NodeParser {
//    this.recordTime = getTagContent(node, "twed:RecordTime");
//    this.stationIdentifier = getTagContent(node, "twed:StationIdentifier");
//    this.waterLevel = getTagContent(node, "twed:WaterLevel");

    private Element node;
    private HashMap<String, String> source;

    public NodeParser(Element node, HashMap<String, String> source ){
        this.node = node;
        this.source = source;
    }

    private String getTagContent(Element node, String tag) {
        return node.getElementsByTagName(tag).item(0).getTextContent();
    }

}

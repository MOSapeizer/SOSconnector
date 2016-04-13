package sosconnector.DTO;

import org.w3c.dom.Element;

/**
 * Created by zil on 2016/4/12.
 */
public class TwedDTO {
    private String recordTime;
    private String stationIdentifier;
    private String waterLevel;

    public TwedDTO(Element node){
        this.recordTime = getTagContent(node, "twed:RecordTime");
        this.stationIdentifier = getTagContent(node, "twed:StationIdentifier");
        this.waterLevel = getTagContent(node, "twed:WaterLevel");
    }

    private String getTagContent(Element node, String tag) {
        return node.getElementsByTagName(tag).item(0).getTextContent();
    }


    public String getRecordTime() {
        return recordTime;
    }

    public String getStationIdentifier() {
        return stationIdentifier;
    }

    public String getWaterLevel() {
        return waterLevel;
    }
}
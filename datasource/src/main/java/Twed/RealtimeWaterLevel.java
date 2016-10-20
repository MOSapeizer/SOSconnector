package Twed;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Zil on 2016/9/26.
 */

@XmlRootElement( name = "RealtimeWaterLevel" )
public class RealtimeWaterLevel {
    private String RecordTime;
    private String StationIdentifier;
    private String WaterLevel;

    @XmlElement( name = "RecordTime" )
    public String getRecordTime() {
        return RecordTime;
    }

    public void setRecordTime(String recordTime) {
        RecordTime = recordTime;
    }

    @XmlElement( name = "StationIdentifier" )
    public String getStationIdentifier() {
        return StationIdentifier;
    }

    public void setStationIdentifier(String stationIdentifier) {
        StationIdentifier = stationIdentifier;
    }

    @XmlElement( name = "WaterLevel" )
    public String getWaterLevel() {
        return WaterLevel;
    }

    public void setWaterLevel(String waterLevel) {
        WaterLevel = waterLevel;
    }
}

package sosconnector.DTO;

/**
 * Created by zil on 2016/4/12.
 */
public class TwedDTO {
    private String recordTime;
    private String stationIdentifier;
    private String waterLevel;

    public TwedDTO(String recordTime, String stationIdentifier, String waterLevel){
        this.recordTime = recordTime;
        this.stationIdentifier = stationIdentifier;
        this.waterLevel = waterLevel;
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
package sosconnector.DTO;

/**
 * Created by zil on 2016/4/26.
 */
public class CwbSeaLevelDTO {
    private String station;
    private String stationId;
    private String[] obsTime;
    private String[] seaLevel;


    public CwbSeaLevelDTO(String station, String stationId, String[] obsTime, String[] seaLevel) {
        this.station = station;
        this.stationId = stationId;
        this.obsTime = obsTime;
        this.seaLevel = seaLevel;
    }

    public String getStation() {
        return station;
    }

    public String getStationId() {
        return stationId;
    }

    public String[] getObsTime() {
        return obsTime;
    }

    public String[] getSeaLevel() {
        return seaLevel;
    }
}

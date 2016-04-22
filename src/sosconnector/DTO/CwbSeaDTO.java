package sosconnector.DTO;

/**
 * Created by zil on 2016/4/22.
 */
public class CwbSeaDTO {
    private String station;
    private String stationId;
    private String obsTime;
    private String depth;
    private String degree;

    public CwbSeaDTO(String station, String stationId, String obsTime, String depth, String degree) {
        this.station = station;
        this.stationId = stationId;
        this.obsTime = obsTime;
        this.depth = depth;
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public String getDepth() {
        return depth;
    }

    public String getStationId() {
        return stationId;
    }

    public String getStation() {
        return station;
    }

    public String getObsTime() {
        return obsTime;
    }
}

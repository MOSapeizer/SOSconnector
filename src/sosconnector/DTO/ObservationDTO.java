package sosconnector.DTO;

/**
 * Created by zil on 2016/6/14.
 */
public class ObservationDTO {
    private String siteName;
    private String longitude;
    private String latitude;
    private Observation[] observations;

    public ObservationDTO(String siteName, String longitude, String latitude, Observation[] observations) {
        this.siteName = siteName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.observations = observations;
    }

    public String getSiteName() {
        return siteName;
    }

    public Observation[] getObservations() {
        return observations;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}

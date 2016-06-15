package sosconnector.DTO;

/**
 * Created by zil on 2016/6/14.
 */
public class ObservationDTO {
    private String offering;
    private String longitude;
    private String latitude;
    private Observation[] observations;

    public ObservationDTO(String offering, String longitude, String latitude, Observation[] observations) {
        this.offering = offering;
        this.longitude = longitude;
        this.latitude = latitude;
        this.observations = observations;
    }

    public String getOffering() {
        return offering;
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

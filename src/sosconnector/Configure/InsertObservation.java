package sosconnector.Configure;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by zil on 2016/6/15.
 */

@XmlType
public class InsertObservation {

    private String longitude;
    private String latitude;
    private Observation[] observation;

    public InsertObservation(){

    }

    public InsertObservation(String longitude, String latitude, Observation[] observation){
        this.longitude = longitude;
        this.latitude = latitude;
        this.observation = observation;
    }

    @XmlElement
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @XmlElement
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @XmlElement
    public Observation[] getObservation() {
        return observation;
    }

    public void setObservation(Observation[] observation) {
        this.observation = observation;
    }

}

package sosconnector.DTO;

/**
 * Created by zil on 2016/4/29.
 */
public class SwcbDTO {
    private final String city;
    private final String district;
    private final String camera;
    private final String cctv_url;
    private final String address;
    private final String lat;
    private final String lon;

    public SwcbDTO( String city, String district, String camera, String cctv_url, String address, String lat, String lon ) {
        this.city = city;
        this.district = district;
        this.camera = camera;
        this.cctv_url = cctv_url;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getCamera() {
        return camera;
    }

    public String getCctv_url() {
        return cctv_url;
    }

    public String getAddress() {
        return address;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}

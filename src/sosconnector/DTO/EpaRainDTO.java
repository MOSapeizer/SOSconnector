package sosconnector.DTO;

/**
 * Created by zil on 2016/6/1.
 */
public class EpaRainDTO {

    private String SiteId;
    private String SiteName;
    private String longitude;
    private String latitude;
    private String RainFallTenMin;
    private String RainFallOneHour;
    private String RainFallThreeHour;
    private String RainFallSixHour;
    private String RainFallHalfDay;
    private String RainFallOneDay;
    private String Now;
    private String timestamp;


    public EpaRainDTO(String siteId, String siteName, String longitude, String latitude, String rainFallTenMin, String rainFallOneHour, String rainFallThreeHour, String rainFallSixHour, String rainFallHalfDay, String rainFallOneDay, String now, String timestamp) {
        SiteId = siteId;
        SiteName = siteName;
        this.longitude = longitude;
        this.latitude = latitude;
        RainFallTenMin = rainFallTenMin;
        RainFallOneHour = rainFallOneHour;
        RainFallThreeHour = rainFallThreeHour;
        RainFallSixHour = rainFallSixHour;
        RainFallHalfDay = rainFallHalfDay;
        RainFallOneDay = rainFallOneDay;
        Now = now;
        this.timestamp = timestamp;
    }

    public String getSiteName() {
        return SiteName;
    }

    public String getRainFallTenMin() {
        return RainFallTenMin;
    }

    public String getRainFallOneHour() {
        return RainFallOneHour;
    }

    public String getRainFallThreeHour() {
        return RainFallThreeHour;
    }

    public String getRainFallSixHour() {
        return RainFallSixHour;
    }

    public String getRainFallHalfDay() {
        return RainFallHalfDay;
    }

    public String getRainFallOneDay() {
        return RainFallOneDay;
    }

    public String getNow() {
        return Now;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getSiteId() {
        return SiteId;
    }
}

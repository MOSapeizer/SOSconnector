package sosconnector.DTO;

/**
 * Created by zil on 2016/3/30.
 */
public class EpaAqxDTO {

    private String CO;
    private String County;
    private String FPMI;
    private String MajorPollutant;
    private String NO;
    private String NO2;
    private String NOx;
    private String O3;
    private String PM10;
    private String PM2_5;
    private String PSI;
    private String PublishTime;
    private String SiteName;
    private String SO2;
    private String Status;
    private String WindSpeed;
    private String WindDirec;

    public EpaAqxDTO(String co, String county, String fpmi, String majorPollutant, String no, String no2, String nOx, String o3, String pm10, String pm2_5, String psi, String publishTime, String siteName, String so2, String status, String windSpeed, String windDirec) {
        CO = co;
        County = county;
        FPMI = fpmi;
        MajorPollutant = majorPollutant;
        NO = no;
        NO2 = no2;
        NOx = nOx;
        O3 = o3;
        PM10 = pm10;
        PM2_5 = pm2_5;
        PSI = psi;
        PublishTime = publishTime;
        SiteName = siteName;
        SO2 = so2;
        Status = status;
        WindSpeed = windSpeed;
        WindDirec = windDirec;
    }


    public String getSiteName(){
        return this.SiteName;
    }

    public String getCounty(){
        return this.County;
    }

    public String getPSI() {
        return PSI;
    }

    public String getMajorPollutant() {
        return MajorPollutant;
    }

    public String getStatus() {
        return Status;
    }

    public String getSO2() {
        return SO2;
    }

    public String getCO() {
        return CO;
    }

    public String getO3() {
        return O3;
    }

    public String getPM10() {
        return PM10;
    }

    public String getPM2_5() {
        return PM2_5;
    }

    public String getNO2() {
        return NO2;
    }

    public String getWindSpeed() {
        return WindSpeed;
    }

    public String getWindDirec() {
        return WindDirec;
    }

    public String getFPMI() {
        return FPMI;
    }

    public String getNOx() {
        return NOx;
    }

    public String getNO() {
        return NO;
    }

    public String getPublishTime() {
        return PublishTime;
    }
}

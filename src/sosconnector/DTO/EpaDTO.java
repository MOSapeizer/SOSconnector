package sosconnector.DTO;

import org.w3c.dom.Element;

/**
 * Created by zil on 2016/3/30.
 */
public class EpaDTO {

    private String County;
    private String PSI;
    private String MajorPollutant;
    private String Status;
    private String SO2;
    private String CO;
    private String O3;
    private String PM10;
    private String PM2_5;
    private String NO2;
    private String WindSpeed;
    private String WindDirec;
    private String FPMI;
    private String NOx;
    private String NO;
    private String PublishTime;
    private String SiteName;

    public EpaDTO(String county, String psi, String majorPollutant, String status, String so2, String co, String o3, String pm10, String pm2_5, String no2, String windSpeed, String windDirec, String fpmi, String nOx, String no, String publishTime, String siteName) {
        County = county;
        PSI = psi;
        MajorPollutant = majorPollutant;
        Status = status;
        SO2 = so2;
        CO = co;
        O3 = o3;
        PM10 = pm10;
        PM2_5 = pm2_5;
        NO2 = no2;
        WindSpeed = windSpeed;
        WindDirec = windDirec;
        FPMI = fpmi;
        NOx = nOx;
        NO = no;
        PublishTime = publishTime;
        SiteName = siteName;
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

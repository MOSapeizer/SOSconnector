package sosconnector;

import org.w3c.dom.Element;

/**
 * Created by zil on 2016/3/30.
 */
public class ObservationDTO {

    private Element node;
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


    public ObservationDTO(Element node){
        this.node = node;
        this.SiteName =  getTagContent("SiteName");
        this.County = getTagContent("County");
        this.PSI = getTagContent("PSI");
        this.MajorPollutant = getTagContent("MajorPollutant");
        this.Status = getTagContent("Status");
        this.SO2 = getTagContent("SO2");
        this.CO = getTagContent("CO");
        this.O3 = getTagContent("O3");
        this.PM10 = getTagContent("PM10");
        this.PM2_5 = getTagContent("PM2.5");
        this.NO2 = getTagContent("NO2");
        this.WindSpeed = getTagContent("WindSpeed");
        this.WindDirec = getTagContent("WindDirec");
        this.FPMI = getTagContent("FPMI");
        this.NOx = getTagContent("NOx");
        this.NO = getTagContent("NO");
        this.PublishTime = getTagContent("PublishTime").replace(" ", "T");
    }

    public String getTagContent(String tag) {
        return node.getElementsByTagName(tag).item(0).getTextContent();
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

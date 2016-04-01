package sosconnector.DTO;

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

    public String observationXML(String id, String[] latLon, String obsName, String obsValue){
        String obsXML = "<sos:observation>\n"
                + "        <om:OM_Observation gml:id=\"o" + id + "\">\n"
                + "            <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement\"/>\n"
                + "            <om:phenomenonTime>\n"
                + "                <gml:TimeInstant gml:id=\"phenomenonTime_" + id + "\">\n"
                + "                    <gml:timePosition>" + PublishTime + "</gml:timePosition>\n"
                + "                </gml:TimeInstant>\n"
                + "            </om:phenomenonTime>\n"
                + "            <om:resultTime xlink:href=\"#phenomenonTime_" + id + "\"/>\n"
                + "            <om:procedure xlink:href=\"urn:ogc:object:feature:Sensor:EPA:sensor" + SiteName + "\"/>\n"
                + "            <om:observedProperty xlink:href=\"urn:ogc:def:phenomenon:OGC:2.0:AQX_" + obsName + "\"/>\n"
                + "            <om:featureOfInterest>\n"
                + "                <sams:SF_SpatialSamplingFeature gml:id=\"SF_SpatialSamplingFeature\">\n"
                + "                    <gml:identifier codeSpace=\"\">" + SiteName + "</gml:identifier>\n"
                + "                    <gml:name>52°North</gml:name>\n"
                + "                    <sf:type xlink:href=\"http://www.opengis.net/def/samplingFeatureType/OGC-OM/2.0/SF_SamplingPoint\"/>\n"
                + "                    <sf:sampledFeature xlink:href=\"http://www.52north.org/test/featureOfInterest/1\"/>\n"
                + "                    <sams:shape>\n"
                + "                        <gml:Point gml:id=\"test_feature_9\">\n"
                + "                            <gml:pos srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\">" + latLon[0] + " " + latLon[1] + "</gml:pos>\n"
                + "                        </gml:Point>\n"
                + "                    </sams:shape>\n"
                + "                </sams:SF_SpatialSamplingFeature>\n"
                + "            </om:featureOfInterest>\n"
                + "            <om:result xsi:type=\"gml:MeasureType\" uom=\"mm\">" + obsValue + "</om:result>\n"
                + "        </om:OM_Observation>\n"
                + "    </sos:observation>\n";
        return obsXML;
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

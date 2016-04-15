package sosconnector.ObservationXML;

import sosconnector.DBManager;
import sosconnector.DTO.EpaDTO;

/**
 * Created by zil on 2016/4/13.
 */
public class EpaXML extends ObservationXML {

    private EpaDTO epa;
    private String[] latLon;

    public EpaXML(){

    }

    public EpaXML(EpaDTO epa){
        this.epa = epa;
        this.latLon = latLon(epa.getSiteName());
    }

    private String[] latLon(String siteName){
        String[] position = new String[2];
        position[0] = DBManager.getInstance().Getlon( siteName );
        position[1] = DBManager.getInstance().Getlat( siteName );
        return position;
    }

    @Override
    protected String setPrefix() {
        return "EPA";
    }

    @Override
    protected String setSiteName() {
        return epa.getSiteName();
    }

    @Override
    protected String[] setProperties() {
        return new String[]{"PSI", "SO2", "CO", "O3", "PM10", "PM2.5", "NO2", "WindSpeed", "WindDirec", "FPMI", "NOx", "NO"};
    }


    @Override
    protected String setPropertyPrefix() {
        return "urn:ogc:def:phenomenon:OGC:2.0:AQX";
    }

    @Override
    public StringBuffer allObservations() {
        StringBuffer allObsString = new StringBuffer();
        allObsString = allObsString.append(SingleObservationXML("1", "PSI", epa.getPSI()));
        allObsString = allObsString.append(SingleObservationXML("2", "SO2", epa.getSO2()));
        allObsString = allObsString.append(SingleObservationXML("3", "CO", epa.getCO()));
        allObsString = allObsString.append(SingleObservationXML("4", "O3", epa.getO3()));
        allObsString = allObsString.append(SingleObservationXML("5", "PM10", epa.getPM10()));
        allObsString = allObsString.append(SingleObservationXML("6", "PM2.5", epa.getPM2_5()));
        allObsString = allObsString.append(SingleObservationXML("7", "NO2", epa.getNO2()));
        allObsString = allObsString.append(SingleObservationXML("8", "WindSpeed", epa.getWindSpeed()));
        allObsString = allObsString.append(SingleObservationXML("9", "WindDirec", epa.getWindDirec()));
        allObsString = allObsString.append(SingleObservationXML("10", "FPMI", epa.getFPMI()));
        allObsString = allObsString.append(SingleObservationXML("11", "NOx", epa.getNOx()));
        allObsString = allObsString.append(SingleObservationXML("12", "NO", epa.getNO()));
        return allObsString;
    }


    private String SingleObservationXML(String id, String obsName, String obsValue){
        String obsXML = "<sos:observation>\n"
                + "        <om:OM_Observation gml:id=\"o" + id + "\">\n"
                + "            <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement\"/>\n"
                + "            <om:phenomenonTime>\n"
                + "                <gml:TimeInstant gml:id=\"phenomenonTime_" + id + "\">\n"
                + "                    <gml:timePosition>" + epa.getPublishTime() + "</gml:timePosition>\n"
                + "                </gml:TimeInstant>\n"
                + "            </om:phenomenonTime>\n"
                + "            <om:resultTime xlink:href=\"#phenomenonTime_" + id + "\"/>\n"
                + "            <om:procedure xlink:href=\"urn:ogc:object:feature:Sensor:EPA:sensor" + epa.getSiteName() + "\"/>\n"
                + "            <om:observedProperty xlink:href=\"urn:ogc:def:phenomenon:OGC:2.0:AQX_" + obsName + "\"/>\n"
                + "            <om:featureOfInterest>\n"
                + "                <sams:SF_SpatialSamplingFeature gml:id=\"SF_SpatialSamplingFeature\">\n"
                + "                    <gml:identifier codeSpace=\"\">" + epa.getSiteName() + "</gml:identifier>\n"
                + "                    <gml:name>" + prefix +"</gml:name>\n"
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


}

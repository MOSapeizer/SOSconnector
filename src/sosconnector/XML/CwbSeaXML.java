package sosconnector.XML;

import sosconnector.DTO.CwbSeaDTO;

/**
 * Created by zil on 2016/4/22.
 */
public class CwbSeaXML extends ObservationXML {

    private final CwbSeaDTO cwb;

    public CwbSeaXML(CwbSeaDTO cwb){
        this.cwb = cwb;
    }

    @Override
    protected String setPrefix() {
        return "CWB";
    }

    @Override
    protected String[] setProperties() {
        return new String[]{ "depth", "temperature" };
    }

    @Override
    protected String setPropertyPrefix() {
        return "urn:ogc:def:phenomenon:OGC:2.0:";
    }

    @Override
    protected String siteName() {
        return cwb.getStation();
    }

    @Override
    protected StringBuffer allObservations() {
        StringBuffer allObs = new StringBuffer();
        allObs.append( singleObservationXML("1", properties[0], "m", cwb.getDepth()) );
        allObs.append( singleObservationXML("2", properties[1], "deg", cwb.getDegree()) );
        return allObs;
    }

    private String timeFix(String time){
        return time.split("\\+")[0];
    }

    //need to parse obsTime;
    private String singleObservationXML(String id, String property, String uom, String value){
        String obsXML = "<sos:observation>\n"
                + "        <om:OM_Observation gml:id=\"o" + id + "\">\n"
                + "            <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement\"/>\n"
                + "            <om:phenomenonTime>\n"
                + "                <gml:TimeInstant gml:id=\"phenomenonTime_" + id + "\">\n"
                + "                    <gml:timePosition>" + timeFix( cwb.getObsTime() ) + "</gml:timePosition>\n"
                + "                </gml:TimeInstant>\n"
                + "            </om:phenomenonTime>\n"
                + "            <om:resultTime xlink:href=\"#phenomenonTime_" + id + "\"/>\n"
                + "            <om:procedure xlink:href=\"urn:ogc:object:feature:Sensor:" + prefix + ":sensor:" + cwb.getStation() + "\"/>\n"
                + "            <om:observedProperty xlink:href=\"" + propertyPrefix + property + "\"/>\n"
                + "            <om:featureOfInterest xlink:href=\"" + cwb.getStation()  + "\"/>\n"
                + "            <om:result xsi:type=\"gml:MeasureType\" uom=\"" + uom + "\">" + value + "</om:result>\n"
                + "        </om:OM_Observation>\n"
                + "    </sos:observation>\n";
        return obsXML;
    }
}

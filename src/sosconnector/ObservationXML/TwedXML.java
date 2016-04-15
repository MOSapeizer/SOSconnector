package sosconnector.ObservationXML;

import sosconnector.DTO.TwedDTO;
import sosconnector.ObservationXML.ObservationXML;

/**
 * Created by zil on 2016/4/12.
 */

// wait for insertSensor.
public class TwedXML extends ObservationXML {

    private TwedDTO twed;

    public TwedXML(TwedDTO twed){
        this.twed = twed;
    }


    @Override
    protected String setPrefix() {
        return "TWED";
    }

    @Override
    protected String setSiteName() {
        return twed.getStationIdentifier();
    }

    @Override
    protected String[] setProperties() {
        return new String[]{"waterLevel"};
    }

    @Override
    protected String setPropertyPrefix() {
        return "urn:ogc:def:phenomenon:OGC:2.0:";
    }

    @Override
    public StringBuffer allObservations() {
        StringBuffer allObs = new StringBuffer();
        // there is a problem.
        allObs.append( singleObservationXML("1", properties[0]) );
        return allObs;
    }

    private String singleObservationXML(String id, String property){
        String obsXML = "<sos:observation>\n"
                + "        <om:OM_Observation gml:id=\"o" + id + "\">\n"
                + "            <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement\"/>\n"
                + "            <om:phenomenonTime>\n"
                + "                <gml:TimeInstant gml:id=\"phenomenonTime_" + id + "\">\n"
                + "                    <gml:timePosition>" + twed.getRecordTime() + "</gml:timePosition>\n"
                + "                </gml:TimeInstant>\n"
                + "            </om:phenomenonTime>\n"
                + "            <om:resultTime xlink:href=\"#phenomenonTime_" + id + "\"/>\n"
                + "            <om:procedure xlink:href=\"urn:ogc:object:feature:Sensor:TWED:sensor:" + twed.getStationIdentifier() + "\"/>\n"
                + "            <om:observedProperty xlink:href=\"urn:ogc:def:phenomenon:OGC:2.0:" + property + "\"/>\n"
                + "            <om:featureOfInterest xlink:href=\"" + twed.getStationIdentifier()  + "\">\n"
                + "            <om:result xsi:type=\"gml:MeasureType\" uom=\"cm\">" + twed.getWaterLevel() + "</om:result>\n"
                + "        </om:OM_Observation>\n"
                + "    </sos:observation>\n";
        return obsXML;
    }
}

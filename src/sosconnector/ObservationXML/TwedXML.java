package sosconnector.ObservationXML;

import sosconnector.DTO.TwedDTO;

/**
 * Created by zil on 2016/4/12.
 */
public class TwedXML {

    private TwedDTO twed;

    public TwedXML(TwedDTO twed){
        this.twed = twed;
    }

    public String singleObservationXML(String id){
        String obsXML = "<sos:observation>\n"
                + "        <om:OM_Observation gml:id=\"o" + id + "\">\n"
                + "            <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement\"/>\n"
                + "            <om:phenomenonTime>\n"
                + "                <gml:TimeInstant gml:id=\"phenomenonTime_" + id + "\">\n"
                + "                    <gml:timePosition>" + twed.getRecordTime() + "</gml:timePosition>\n"
                + "                </gml:TimeInstant>\n"
                + "            </om:phenomenonTime>\n"
                + "            <om:resultTime xlink:href=\"#phenomenonTime_" + id + "\"/>\n"
                + "            <om:procedure xlink:href=\"urn:ogc:object:feature:Sensor:TWED:sensor" + twed.getStationIdentifier() + "\"/>\n"
                + "            <om:observedProperty xlink:href=\"urn:ogc:def:phenomenon:OGC:2.0:waterLevel\"/>\n"
                + "            <om:featureOfInterest xlink:href=\"" + twed.getStationIdentifier()  + "\">\n"
                + "            <om:result xsi:type=\"gml:MeasureType\" uom=\"cm\">" + twed.getWaterLevel() + "</om:result>\n"
                + "        </om:OM_Observation>\n"
                + "    </sos:observation>\n";
        return obsXML;
    }
}

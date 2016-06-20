package sosconnector.XML;

/**
 * Created by zil on 2016/4/12.
 */

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
    protected String[] setProperties() {
        return new String[]{"waterLevel"};
    }

    @Override
    protected String setPropertyPrefix() {
        return "urn:ogc:def:phenomenon:OGC:2.0:";
    }

    @Override
    protected String siteName() {
        return twed.getStationIdentifier();
    }

    @Override
    public StringBuffer allObservations() {
        StringBuffer allObs = new StringBuffer();
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
                + "            <om:procedure xlink:href=\"urn:ogc:object:feature:Sensor:" + prefix + ":sensor" + twed.getStationIdentifier() + "\"/>\n"
                + "            <om:observedProperty xlink:href=\"" + propertyPrefix + "_" + property + "\"/>\n"
                + "            <om:featureOfInterest xlink:href=\"" + twed.getStationIdentifier()  + "\"/>\n"
                + "            <om:result xsi:type=\"gml:MeasureType\" uom=\"m\">" + twed.getWaterLevel() + "</om:result>\n"
                + "        </om:OM_Observation>\n"
                + "    </sos:observation>\n";
        return obsXML;
    }
}

package sosconnector.XML;

/**
 * Created by zil on 2016/6/6.
 */
public class CwbSeaLevelXML extends ObservationXML {

    private final CwbSeaLevelDTO cwb;

    public CwbSeaLevelXML(CwbSeaLevelDTO cwb){
        this.cwb = cwb;
    }

    @Override
    protected String setPrefix() {
        return "CWB";
    }

    @Override
    protected String[] setProperties() {
        return new String[]{ "SeaLevel" };
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
        String[] seaLevel = cwb.getSeaLevel();
        String[] obsTime = cwb.getObsTime();
        for( Integer i=0 ; i < seaLevel.length ; i++ ){
            allObs.append( singleObservationXML(i, properties[0], obsTime[i], seaLevel[i]) );
        }
        return allObs;
    }

    private String timeFix(String time){
        return time.split("\\+")[0];
    }

    //need to parse obsTime;
    private String singleObservationXML(int id, String property, String timestamp, String value){
        String obsXML = "<sos:observation>\n"
                + "        <om:OM_Observation gml:id=\"o" + id + "\">\n"
                + "            <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement\"/>\n"
                + "            <om:phenomenonTime>\n"
                + "                <gml:TimeInstant gml:id=\"phenomenonTime_" + id + "\">\n"
                + "                    <gml:timePosition>" + timeFix( timestamp ) + "</gml:timePosition>\n"
                + "                </gml:TimeInstant>\n"
                + "            </om:phenomenonTime>\n"
                + "            <om:resultTime xlink:href=\"#phenomenonTime_" + id + "\"/>\n"
                + "            <om:procedure xlink:href=\"urn:ogc:object:feature:Sensor:" + prefix + ":sensor:" + cwb.getStation() + "\"/>\n"
                + "            <om:observedProperty xlink:href=\"" + propertyPrefix + property + "\"/>\n"
                + "            <om:featureOfInterest xlink:href=\"" + cwb.getStation()  + "\"/>\n"
                + "            <om:result xsi:type=\"gml:MeasureType\" uom=\"cm\">" + value + "</om:result>\n"
                + "        </om:OM_Observation>\n"
                + "    </sos:observation>\n";
        return obsXML;
    }
}

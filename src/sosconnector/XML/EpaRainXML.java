package sosconnector.XML;

/**
 * Created by zil on 2016/6/1.
 */
public class EpaRainXML extends ObservationXML {

    private EpaRainDTO epa;

    public EpaRainXML(EpaRainDTO epa) {
        this.epa = epa;
    }

    @Override
    protected String setPrefix() {
        return "EPA";
    }

    @Override
    protected String[] setProperties() {
        return new String[]{ "rainfall_10min",
                             "rainfall_1hr",
                             "rainfall_3hr",
                             "rainfall_6hr",
                             "rainfall_now",
                             "rainfall_12hr",
                             "rainfall_24hr" };
    }

    @Override
    protected String setPropertyPrefix() {
        return "urn:ogc:def:phenomenon:OGC:2.0:";
    }

    @Override
    protected String siteName() {
        return epa.getSiteName();
    }

    @Override
    protected StringBuffer allObservations() {
        StringBuffer allObs = new StringBuffer();
        allObs.append( singleObservationXML("1", "rainfall_10min", epa.getRainFallTenMin() ));
        allObs.append( singleObservationXML("2", "rainfall_1hr", epa.getRainFallOneHour() ));
        allObs.append( singleObservationXML("3", "rainfall_3hr", epa.getRainFallThreeHour() ));
        allObs.append( singleObservationXML("4", "rainfall_6hr", epa.getRainFallSixHour() ));
        allObs.append( singleObservationXML("5", "rainfall_12hr", epa.getRainFallHalfDay() ));
        allObs.append( singleObservationXML("6", "rainfall_24hr", epa.getRainFallOneDay() ));
        allObs.append( singleObservationXML("7", "rainfall_now", epa.getNow() ));
        return allObs;
    }

    private String singleObservationXML(String id, String property, String rain){
        String obsXML = "<sos:observation>\n"
                + "        <om:OM_Observation gml:id=\"o" + id + "\">\n"
                + "            <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_TextObservation\"/>\n"
                + "            <om:phenomenonTime>\n"
                + "                <gml:TimeInstant gml:id=\"phenomenonTime_" + id + "\">\n"
                + "                    <gml:timePosition indeterminatePosition=\"now\">" + epa.getTimestamp() + "</gml:timePosition>\n"
                + "                </gml:TimeInstant>\n"
                + "            </om:phenomenonTime>\n"
                + "            <om:resultTime xlink:href=\"#phenomenonTime_" + id + "\"/>\n"
                + "            <om:procedure xlink:href=\"urn:ogc:object:feature:Sensor:"+ prefix +":sensor:" + siteName() + "\"/>\n"
                + "            <om:observedProperty xlink:href=\"" + propertyPrefix + property + "\"/>\n"
                + "            <om:featureOfInterest>\n"
                + "                <sams:SF_SpatialSamplingFeature gml:id=\"CCTV\">\n"
                + "                    <gml:identifier codeSpace=\"\">" + siteName() + "</gml:identifier>\n"
                + "                    <gml:name>" + prefix +"</gml:name>\n"
                + "                    <sf:type xlink:href=\"http://www.opengis.net/def/samplingFeatureType/OGC-OM/2.0/SF_SamplingPoint\"/>\n"
                + "                    <sf:sampledFeature xlink:href=\"http://www.52north.org/test/featureOfInterest/1\"/>\n"
                + "                    <sams:shape>\n"
                + "                        <gml:Point gml:id=\"feature_9\">\n"
                + "                            <gml:pos srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\">" + epa.getLongitude() + " " + epa.getLatitude() + "</gml:pos>\n"
                + "                        </gml:Point>\n"
                + "                    </sams:shape>\n"
                + "                </sams:SF_SpatialSamplingFeature>\n"
                + "            </om:featureOfInterest>\n"
                + "            <om:result xsi:type=\"gml:MeasureType\" uom=\"mm\">" + rain + "</om:result>\n"
                + "        </om:OM_Observation>\n"
                + "    </sos:observation>\n";
        return obsXML;
    }
}

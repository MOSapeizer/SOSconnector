package sosconnector.XML;

import sosconnector.DTO.SwcbDTO;

/**
 * Created by zil on 2016/4/29.
 */
public class SwcbXML extends ObservationXML {

    private final SwcbDTO swcb;

    public SwcbXML(SwcbDTO swcb ){
        this.swcb = swcb;
    }

    @Override
    protected String setPrefix() {
        return "SWCB";
    }

    @Override
    protected String[] setProperties() {
        return new String[] { "CCTV" };
    }

    @Override
    protected String setPropertyPrefix() {
        return "urn:ogc:def:phenomenon:OGC:2.0:";
    }

    @Override
    protected String siteName() {
        return swcb.getCamera();
    }

    @Override
    protected StringBuffer allObservations() {
        StringBuffer allObs = new StringBuffer();
        allObs.append( singleObservationXML("1", properties[0]) );
        return allObs;
    }

    @Override
    public String getInsertSensorXml() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<swes:InsertSensor\n"
                + "    xmlns:swes=\"http://www.opengis.net/swes/2.0\"\n"
                + "    xmlns:sos=\"http://www.opengis.net/sos/2.0\"\n"
                + "    xmlns:swe=\"http://www.opengis.net/swe/1.0.1\"\n"
                + "    xmlns:sml=\"http://www.opengis.net/sensorML/1.0.1\"\n"
                + "    xmlns:gml=\"http://www.opengis.net/gml\"\n"
                + "    xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n"
                + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" service=\"SOS\" version=\"2.0.0\" xsi:schemaLocation=\"http://www.opengis.net/sos/2.0 http://schemas.opengis.net/sos/2.0/sosInsertSensor.xsd   http://www.opengis.net/swes/2.0 http://schemas.opengis.net/swes/2.0/swes.xsd\">\n"
                + "    <swes:procedureDescriptionFormat>http://www.opengis.net/sensorML/1.0.1</swes:procedureDescriptionFormat>\n"
                + "    <swes:procedureDescription>\n"
                + "        <sml:SensorML version=\"1.0.1\">\n"
                + "            <sml:member>\n"
                + "                <sml:System>\n"
                + "                    <!-- optional; generated if not present -->\n"
                + "                    <sml:identification>\n"
                + "                        <sml:IdentifierList>\n"
                + "                            <sml:identifier name=\"uniqueID\">\n"
                + "                                <sml:Term definition=\"urn:ogc:def:identifier:OGC:1.0:uniqueID\">\n"
                + "                                    <sml:value>urn:ogc:object:feature:Sensor:"+ prefix +":sensor:" + siteName() + "</sml:value>\n"
                + "                                </sml:Term>\n"
                + "                            </sml:identifier>\n"
                + "                        </sml:IdentifierList>\n"
                + "                    </sml:identification>\n"
                + "                    <sml:capabilities name=\"offerings\">\n"
                + "                        <swe:SimpleDataRecord>\n"
                + "                            <!-- Field name or gml:name is used for the offering's name -->\n"
                + "                            <swe:field name=\"" + siteName() + "\">\n"
                + "                                <swe:Text definition=\"urn:ogc:def:identifier:OGC:offeringID\">\n"
                + "                                    <gml:name>" + siteName() + "</gml:name>\n"
                + "                                    <swe:value>" + siteName() + "</swe:value>\n"
                + "                                </swe:Text>\n"
                + "                            </swe:field>\n"
                + "                        </swe:SimpleDataRecord>\n"
                + "                    </sml:capabilities>\n"
                + "                </sml:System>\n"
                + "            </sml:member>\n"
                + "        </sml:SensorML>\n"
                + "    </swes:procedureDescription>\n"
                + "    <!-- multiple values possible -->\n"
                + observablePropertyGroup()
                + "    <swes:metadata>\n"
                + "        <sos:SosInsertionMetadata>\n"
                + "            <sos:observationType>http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_TextObservation</sos:observationType>\n"
                + "            <!-- multiple values possible -->\n"
                + "           \n"
                + "        </sos:SosInsertionMetadata>\n"
                + "    </swes:metadata>\n"
                + "</swes:InsertSensor>";
        return xml;

    }

    private String singleObservationXML(String id, String property){
        String obsXML = "<sos:observation>\n"
                + "        <om:OM_TextObservation gml:id=\"o" + id + "\">\n"
                + "            <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement\"/>\n"
                + "            <om:phenomenonTime>\n"
                + "                <gml:TimeInstant gml:id=\"phenomenonTime_" + id + "\">\n"
                + "                    <gml:timePosition>" + "2016-04-11T16:22:25.00" + "</gml:timePosition>\n"
                + "                </gml:TimeInstant>\n"
                + "            </om:phenomenonTime>\n"
                + "            <om:resultTime xlink:href=\"#phenomenonTime_" + id + "\"/>\n"
                + "            <om:procedure xlink:href=\"urn:ogc:object:feature:Sensor:EPA:sensor" + swcb.getCamera() + "\"/>\n"
                + "            <om:observedProperty xlink:href=\"" + propertyPrefix + "_" + property + "\"/>\n"
                + "            <om:featureOfInterest>\n"
                + "                <sams:SF_SpatialSamplingFeature gml:id=\"CCTV\">\n"
                + "                    <gml:identifier codeSpace=\"\">" + swcb.getCamera() + "</gml:identifier>\n"
                + "                    <gml:name>" + prefix +"</gml:name>\n"
                + "                    <sf:type xlink:href=\"http://www.opengis.net/def/samplingFeatureType/OGC-OM/2.0/SF_SamplingPoint\"/>\n"
                + "                    <sf:sampledFeature xlink:href=\"http://www.52north.org/test/featureOfInterest/1\"/>\n"
                + "                    <sams:shape>\n"
                + "                        <gml:Point gml:id=\"feature_9\">\n"
                + "                            <gml:pos srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\">" + swcb.getLon() + " " + swcb.getLat() + "</gml:pos>\n"
                + "                        </gml:Point>\n"
                + "                    </sams:shape>\n"
                + "                </sams:SF_SpatialSamplingFeature>\n"
                + "            </om:featureOfInterest>\n"
                + "            <om:result>" + swcb.getCctv_url() + "</om:result>\n"
                + "        </om:OM_TextObservation>\n"
                + "    </sos:observation>\n";
        return obsXML;
    }
}
package sosconnector.ObservationXML;

/**
 * Created by zil on 2016/4/13.
 */
abstract class ObservationXML {
    String prefix  = setPrefix();
    private String[] properties = setProperties();
    private String propertyPrefix = setPropertyPrefix();
    protected abstract String setPrefix();
    protected abstract String setSiteName();
    protected abstract String[] setProperties();
    protected abstract String setPropertyPrefix();

    //如果有n個property就要append n個singleObservationXML
    //待解的隱患
    protected abstract StringBuffer allObservations();

    public String getInsertSensorXml(String siteName) {
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
                + "                                    <sml:value>urn:ogc:object:feature:Sensor:"+ prefix +":sensor" + siteName + "</sml:value>\n"
                + "                                </sml:Term>\n"
                + "                            </sml:identifier>\n"
                + "                        </sml:IdentifierList>\n"
                + "                    </sml:identification>\n"
                + "                    <sml:capabilities name=\"offerings\">\n"
                + "                        <swe:SimpleDataRecord>\n"
                + "                            <!-- Field name or gml:name is used for the offering's name -->\n"
                + "                            <swe:field name=\"" + siteName+ "\">\n"
                + "                                <swe:Text definition=\"urn:ogc:def:identifier:OGC:offeringID\">\n"
                + "                                    <gml:name>" + siteName + "</gml:name>\n"
                + "                                    <swe:value>" + siteName + "</swe:value>\n"
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
                + "            <sos:observationType>http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement</sos:observationType>\n"
                + "            <!-- multiple values possible -->\n"
                + "           \n"
                + "        </sos:SosInsertionMetadata>\n"
                + "    </swes:metadata>\n"
                + "</swes:InsertSensor>";
        return xml;

    }

    public String getInsertObservationXML(){
        return ObservationXML.getInsertObservationXml(setSiteName(), allObservations());
    }

    private StringBuffer observablePropertyGroup(){
        StringBuffer group = new StringBuffer();
        for(String property: properties)
            group.append( observablePropertyTag(property) );
        return group;
    }

    private static String getInsertObservationXml(String stationName, StringBuffer allObsString) {
        String insertobservationxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<sos:InsertObservation\n"
                + "    xmlns:sos=\"http://www.opengis.net/sos/2.0\"\n"
                + "    xmlns:swes=\"http://www.opengis.net/swes/2.0\"\n"
                + "    xmlns:swe=\"http://www.opengis.net/swe/2.0\"\n"
                + "    xmlns:sml=\"http://www.opengis.net/sensorML/1.0.1\"\n"
                + "    xmlns:gml=\"http://www.opengis.net/gml/3.2\"\n"
                + "    xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n"
                + "    xmlns:om=\"http://www.opengis.net/om/2.0\"\n"
                + "    xmlns:sams=\"http://www.opengis.net/samplingSpatial/2.0\"\n"
                + "    xmlns:sf=\"http://www.opengis.net/sampling/2.0\"\n"
                + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" service=\"SOS\" version=\"2.0.0\""
                + " xsi:schemaLocation=\"http://www.opengis.net/sos/2.0 http://schemas.opengis.net/sos/2.0/sos.xsd"
                + " http://www.opengis.net/samplingSpatial/2.0 http://schemas.opengis.net/samplingSpatial/2.0/spatialSamplingFeature.xsd\">\n"
                + "    <sos:offering>" + stationName + "</sos:offering>\n"
                + allObsString
                + "</sos:InsertObservation>";
        return insertobservationxml;
    }

    private String observablePropertyTag(String property){
        return "<swes:observableProperty>" + propertyPrefix + "_" + property + "</swes:observableProperty>";
    }
}

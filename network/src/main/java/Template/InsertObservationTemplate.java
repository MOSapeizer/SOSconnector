package Template;

/**
 * Created by Zil on 2016/9/26.
 */
public class InsertObservationTemplate {

    public static String base = "<sos:InsertObservation\n" +
            "            xmlns:sos=\"http://www.opengis.net/sos/2.0\"\n" +
            "            xmlns:swes=\"http://www.opengis.net/swes/2.0\"\n" +
            "            xmlns:swe=\"http://www.opengis.net/swe/2.0\"\n" +
            "            xmlns:sml=\"http://www.opengis.net/sensorML/1.0.1\"\n" +
            "            xmlns:gml=\"http://www.opengis.net/gml/3.2\"\n" +
            "            xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" +
            "            xmlns:om=\"http://www.opengis.net/om/2.0\"\n" +
            "            xmlns:sams=\"http://www.opengis.net/samplingSpatial/2.0\"\n" +
            "            xmlns:sf=\"http://www.opengis.net/sampling/2.0\"\n" +
            "            xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
            "            xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" service=\"SOS\" version=\"2.0.0\" xsi:schemaLocation=\"http://www.opengis.net/sos/2.0 http://schemas.opengis.net/sos/2.0/sos.xsd            http://www.opengis.net/samplingSpatial/2.0 http://schemas.opengis.net/samplingSpatial/2.0/spatialSamplingFeature.xsd\">\n" +
            "            <!-- multiple offerings are possible -->\n" +
            "            <sos:offering>上安全景式攝影機</sos:offering>\n" +
            "            <sos:observation>\n" +
            "                <om:OM_Observation gml:id=\"o1\">\n" +
            "                    <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_TextObservation\"/>\n" +
            "                    <om:phenomenonTime>\n" +
            "                        <gml:TimeInstant gml:id=\"phenomenonTime\">\n" +
            "                            <gml:timePosition>  </gml:timePosition>\n" +
            "                        </gml:TimeInstant>\n" +
            "                    </om:phenomenonTime>\n" +
            "                    <om:resultTime xlink:href=\"#phenomenonTime\"/>\n" +
            "                    <om:procedure xlink:href=\"urn:ogc:object:feature:Sensor:SWCB:sensor:上安全景式攝影機\"/>\n" +
            "                    <om:observedProperty xlink:href=\"http://www.52north.org/test/observableProperty/9_4\"/>\n" +
            "                    <om:featureOfInterest>\n" +
            "                        <sams:SF_SpatialSamplingFeature gml:id=\"ssf_test_feature_9\">\n" +
            "                            <gml:identifier codeSpace=\"\">http://www.52north.org/test/featureOfInterest/9</gml:identifier>\n" +
            "                            <gml:name>52°North</gml:name>\n" +
            "                            <sf:type xlink:href=\"http://www.opengis.net/def/samplingFeatureType/OGC-OM/2.0/SF_SamplingPoint\"/>\n" +
            "                            <sf:sampledFeature xlink:href=\"http://www.52north.org/test/featureOfInterest/1\"/>\n" +
            "                            <sams:shape>\n" +
            "                                <gml:Point gml:id=\"test_feature_9\">\n" +
            "                                    <gml:pos srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\">51.935101100104916 7.651968812254194</gml:pos>\n" +
            "                                </gml:Point>\n" +
            "                            </sams:shape>\n" +
            "                        </sams:SF_SpatialSamplingFeature>\n" +
            "                    </om:featureOfInterest>\n" +
            "                  <om:result xsi:type=\"xs:string\">http://test_url.com.tw/</om:result>\n" +
            "                </om:OM_Observation>\n" +
            "            </sos:observation>\n" +
            "        </sos:InsertObservation>";


    public static String sample_output(String offering, String type, String timePosition, String procedure, String observedProperty, String foiID, String foiName, String position, String resultType, String uom, String result){
        return "<sos:InsertObservation\n" +
                "            xmlns:sos=\"http://www.opengis.net/sos/2.0\"\n" +
                "            xmlns:swes=\"http://www.opengis.net/swes/2.0\"\n" +
                "            xmlns:swe=\"http://www.opengis.net/swe/2.0\"\n" +
                "            xmlns:sml=\"http://www.opengis.net/sensorML/1.0.1\"\n" +
                "            xmlns:gml=\"http://www.opengis.net/gml/3.2\"\n" +
                "            xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" +
                "            xmlns:om=\"http://www.opengis.net/om/2.0\"\n" +
                "            xmlns:sams=\"http://www.opengis.net/samplingSpatial/2.0\"\n" +
                "            xmlns:sf=\"http://www.opengis.net/sampling/2.0\"\n" +
                "            xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                "            xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" service=\"SOS\" version=\"2.0.0\" xsi:schemaLocation=\"http://www.opengis.net/sos/2.0 http://schemas.opengis.net/sos/2.0/sos.xsd            http://www.opengis.net/samplingSpatial/2.0 http://schemas.opengis.net/samplingSpatial/2.0/spatialSamplingFeature.xsd\">\n" +
                "            <!-- multiple offerings are possible -->\n" +
                "            <sos:offering>" + offering +"</sos:offering>\n" +
                "            <sos:observation>\n" +
                "                <om:OM_Observation gml:id=\"o1\">\n" +
                "                    <om:type xlink:href=\""+type+"\"/>\n" +
                "                    <om:phenomenonTime>\n" +
                "                        <gml:TimeInstant gml:id=\"phenomenonTime\">\n" +
                "                            <gml:timePosition> "+ timePosition +" </gml:timePosition>\n" +
                "                        </gml:TimeInstant>\n" +
                "                    </om:phenomenonTime>\n" +
                "                    <om:resultTime xlink:href=\"#phenomenonTime\"/>\n" +
                "                    <om:procedure xlink:href=\""+ procedure +"\"/>\n" +
                "                    <om:observedProperty xlink:href=\""+observedProperty+"\"/>\n" +
                "                    <om:featureOfInterest>\n" +
                "                        <sams:SF_SpatialSamplingFeature gml:id=\"ssf_test_feature_9\">\n" +
                "                            <gml:identifier codeSpace=\"\">"+foiID+"</gml:identifier>\n" +
                "                            <gml:name>"+foiName+"</gml:name>\n" +
                "                            <sf:type xlink:href=\"http://www.opengis.net/def/samplingFeatureType/OGC-OM/2.0/SF_SamplingPoint\"/>\n" +
                "                            <sf:sampledFeature xlink:href=\"http://www.52north.org/test/featureOfInterest/1\"/>\n" +
                "                            <sams:shape>\n" +
                "                                <gml:Point gml:id=\"test_feature_9\">\n" +
                "                                    <gml:pos srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\">"+position+"</gml:pos>\n" +
                "                                </gml:Point>\n" +
                "                            </sams:shape>\n" +
                "                        </sams:SF_SpatialSamplingFeature>\n" +
                "                    </om:featureOfInterest>\n" +
                "                  <om:result xsi:type=\""+resultType+"\" uom=\""+ uom +"\">"+result+"</om:result>\n" +
                "                </om:OM_Observation>\n" +
                "            </sos:observation>\n" +
                "        </sos:InsertObservation>";
    }
}

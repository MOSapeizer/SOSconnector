package sosconnector.XML;

import sosconnector.DTO.Observation;
import sosconnector.DTO.ObservationDTO;
import sosconnector.DTO.SiteDTO;

/**
 * Created by zil on 2016/6/15.
 */
public class XmlTemplate {

    private SiteDTO site;
    private ObservationDTO observations;

    public XmlTemplate(SiteDTO site, ObservationDTO observations){
        this.site = site;
        this.observations = observations;
    }

    public String getInsertSensorXml() {
        String siteName = site.getOffering();
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<swes:InsertSensor\n"
                + "    xmlns:swes=\"http://www.opengis.net/swes/2.0\"\n"
                + "    xmlns:sos=\"http://www.opengis.net/sos/2.0\"\n"
                + "    xmlns:swe=\"http://www.opengis.net/swe/1.0.1\"\n"
                + "    xmlns:sml=\"http://www.opengis.net/sensorML/1.0.1\"\n"
                + "    xmlns:gml=\"http://www.opengis.net/gml\"\n"
                + "    xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n"
                + "    xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n"
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
                + "                                    <sml:value>urn:ogc:object:feature:Sensor:"+ site.getPrefix() +":sensor:" + siteName + "</sml:value>\n"
                + "                                </sml:Term>\n"
                + "                            </sml:identifier>\n"
                + "                        </sml:IdentifierList>\n"
                + "                    </sml:identification>\n"
                + "                    <sml:capabilities name=\"offerings\">\n"
                + "                        <swe:SimpleDataRecord>\n"
                + "                            <!-- Field name or gml:name is used for the offering's name -->\n"
                + "                            <swe:field name=\"" + siteName + "\">\n"
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
                +      observablePropertyGroup()
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

    public String getInsertObservationXml() {
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
                + "    xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n"
                + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" service=\"SOS\" version=\"2.0.0\""
                + " xsi:schemaLocation=\"http://www.opengis.net/sos/2.0 http://schemas.opengis.net/sos/2.0/sos.xsd"
                + " http://www.opengis.net/samplingSpatial/2.0 http://schemas.opengis.net/samplingSpatial/2.0/spatialSamplingFeature.xsd\">\n"
                + "    <sos:offering>" + observations.getOffering() + "</sos:offering>\n"
                + observationGroup()
                + "</sos:InsertObservation>";
        return insertobservationxml;
    }

    private String observationGroup() {
        Integer index = 1;
        StringBuffer allObs = new StringBuffer();
        for (Observation observation : observations.getObservations()) {
            allObs.append(observationXML(index.toString(), observation));
            index += 1;
        }
        return allObs.toString();
    }

    private String observationXML(String index, Observation observation){
        String obsXML = "<sos:observation>\n"
                + "        <om:OM_Observation gml:id=\"o" + index + "\">\n"
                + "            <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement\"/>\n"
                + "            <om:phenomenonTime>\n"
                + "                <gml:TimeInstant gml:id=\"phenomenonTime_" + index + "\">\n"
                + "                    <gml:timePosition>" + observation.getTimestamp() + "</gml:timePosition>\n"
                + "                </gml:TimeInstant>\n"
                + "            </om:phenomenonTime>\n"
                + "            <om:resultTime xlink:href=\"#phenomenonTime_" + index + "\"/>\n"
                + "            <om:procedure xlink:href=\"urn:ogc:object:feature:Sensor:" + site.getPrefix() + ":sensor:" + observations.getOffering() + "\"/>\n"
                + "            <om:observedProperty xlink:href=\"" + site.getPropertyPrefix() + observation.getName() + "\"/>\n"
                + "            <om:featureOfInterest xlink:href=\"" + observations.getOffering()  + "\"/>\n"
                +              result( observation.getType(), observation )
                + "        </om:OM_Observation>\n"
                + "    </sos:observation>\n";
        return obsXML;
    }

    private String result(String type, Observation observation){
        String result = "            ";
        if(type.equals("xs:string")) {
            result += "<om:result xsi:type=\"xs:string\">"
                    + observation.getValue().replaceAll("&", "&amp;")
                    + "</om:result>\n";
        }
        else if(type.equals("gml:MeasureType")){
            result += "<om:result xsi:type="
                    + type
                    + "\" uom=\"" + observation.getUnit()
                    + "\">" + observation.getValue()
                    + "</om:result>";
        }
        return result;
    }

    protected StringBuffer observablePropertyGroup() {
        StringBuffer group = new StringBuffer();
        for (String property : site.getProperty())
            group.append(observablePropertyTag(property));
        return group;
    }

    private String observablePropertyTag(String property) {
        return "<swes:observableProperty>" + site.getPropertyPrefix() + property + "</swes:observableProperty>";
    }
}

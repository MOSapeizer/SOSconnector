package sosconnector.ObservationXML;

import sosconnector.DBManager;
import sosconnector.DTO.EpaDTO;
import sosconnector.DTO.EpaSiteDTO;
import sosconnector.Request.InsertRequest;

/**
 * Created by zil on 2016/4/13.
 */
public class EpaXML implements ObservationXML {
    private EpaDTO epa;
    private String[] latLon;

    public EpaXML(){

    }

    public EpaXML(EpaDTO epa){
        this.epa = epa;
        this.latLon = latLon(epa.getSiteName());
    }

    private String[] latLon(String siteName){
        String[] position = new String[2];
        position[0] = DBManager.getInstance().Getlon( siteName );
        position[1] = DBManager.getInstance().Getlat( siteName );
        return position;
    }

    public String getInsertObservationXML(){
        String insertObservationXml = InsertRequest.getInsertObservationXml(epa.getSiteName(), allObservations());
        return insertObservationXml;
    }


    public StringBuffer allObservations(){
        StringBuffer allObsString = new StringBuffer();
        allObsString = allObsString.append( SingleObservationXML("1", "PSI", epa.getPSI() ) );
        allObsString = allObsString.append( SingleObservationXML("2", "SO2", epa.getSO2() ));
        allObsString = allObsString.append( SingleObservationXML("3", "CO", epa.getCO() ));
        allObsString = allObsString.append( SingleObservationXML("4", "O3", epa.getO3() ));
        allObsString = allObsString.append( SingleObservationXML("5", "PM10", epa.getPM10() ));
        allObsString = allObsString.append( SingleObservationXML("6", "PM2.5", epa.getPM2_5() ));
        allObsString = allObsString.append( SingleObservationXML("7", "NO2", epa.getNO2() ));
        allObsString = allObsString.append( SingleObservationXML("8", "WindSpeed", epa.getWindSpeed() ));
        allObsString = allObsString.append( SingleObservationXML("9", "WindDirec", epa.getWindDirec() ));
        allObsString = allObsString.append( SingleObservationXML("10", "FPMI", epa.getFPMI() ));
        allObsString = allObsString.append( SingleObservationXML("11", "NOx", epa.getNOx() ));
        allObsString = allObsString.append( SingleObservationXML("12", "NO", epa.getNO() ));
        return allObsString;
    }

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
                + "                                    <sml:value>urn:ogc:object:feature:Sensor:EPA:sensor" + siteName + "</sml:value>\n"
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
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_PSI</swes:observableProperty>\n"
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_SO2</swes:observableProperty>\n"
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_CO</swes:observableProperty>\n"
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_O3</swes:observableProperty>\n"
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_PM10</swes:observableProperty>\n"
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_PM2.5</swes:observableProperty>\n"
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_NO2</swes:observableProperty>\n"
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_WindSpeed</swes:observableProperty>\n"
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_WindDirec</swes:observableProperty>\n"
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_FPMI</swes:observableProperty>\n"
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_NOx</swes:observableProperty>\n"
                + "    <swes:observableProperty>urn:ogc:def:phenomenon:OGC:2.0:AQX_NO</swes:observableProperty>\n"
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

    private String SingleObservationXML(String id, String obsName, String obsValue){
        String obsXML = "<sos:observation>\n"
                + "        <om:OM_Observation gml:id=\"o" + id + "\">\n"
                + "            <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement\"/>\n"
                + "            <om:phenomenonTime>\n"
                + "                <gml:TimeInstant gml:id=\"phenomenonTime_" + id + "\">\n"
                + "                    <gml:timePosition>" + epa.getPublishTime() + "</gml:timePosition>\n"
                + "                </gml:TimeInstant>\n"
                + "            </om:phenomenonTime>\n"
                + "            <om:resultTime xlink:href=\"#phenomenonTime_" + id + "\"/>\n"
                + "            <om:procedure xlink:href=\"urn:ogc:object:feature:Sensor:EPA:sensor" + epa.getSiteName() + "\"/>\n"
                + "            <om:observedProperty xlink:href=\"urn:ogc:def:phenomenon:OGC:2.0:AQX_" + obsName + "\"/>\n"
                + "            <om:featureOfInterest>\n"
                + "                <sams:SF_SpatialSamplingFeature gml:id=\"SF_SpatialSamplingFeature\">\n"
                + "                    <gml:identifier codeSpace=\"\">" + epa.getSiteName() + "</gml:identifier>\n"
                + "                    <gml:name>52°North</gml:name>\n"
                + "                    <sf:type xlink:href=\"http://www.opengis.net/def/samplingFeatureType/OGC-OM/2.0/SF_SamplingPoint\"/>\n"
                + "                    <sf:sampledFeature xlink:href=\"http://www.52north.org/test/featureOfInterest/1\"/>\n"
                + "                    <sams:shape>\n"
                + "                        <gml:Point gml:id=\"test_feature_9\">\n"
                + "                            <gml:pos srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\">" + latLon[0] + " " + latLon[1] + "</gml:pos>\n"
                + "                        </gml:Point>\n"
                + "                    </sams:shape>\n"
                + "                </sams:SF_SpatialSamplingFeature>\n"
                + "            </om:featureOfInterest>\n"
                + "            <om:result xsi:type=\"gml:MeasureType\" uom=\"mm\">" + obsValue + "</om:result>\n"
                + "        </om:OM_Observation>\n"
                + "    </sos:observation>\n";
        return obsXML;
    }
}

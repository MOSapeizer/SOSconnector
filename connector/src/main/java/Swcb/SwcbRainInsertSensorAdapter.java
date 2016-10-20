package Swcb;

import helper.InsertSensorML20Helper;
import insertSensorML20.*;

/**
 * Created by Zil on 2016/10/18.
 */
public class SwcbRainInsertSensorAdapter {


    private Row rainStation;
    private InsertSensor insertSensor = new InsertSensor();
    private InsertSensorML20Helper helper = new InsertSensorML20Helper();
    private String prefix = "urn:ogc:object:feature:sensor:swcb";

    public SwcbRainInsertSensorAdapter(Row rainStation) {
        this.rainStation = rainStation;
    }

    public InsertSensor getInsertSensor(){
        before();
        setIdentifier();
        setIdentification();
        setCharacteristics();
        setCapabilities();
        setDocumentation();
        setInput();
        setOutput();
        setObservableProperties();
        setObservationTypes();
        return insertSensor;
    }

    private void before() {

    }

    private void setIdentifier() {
        Identifier identifier = new Identifier();
        identifier.setPrefix(prefix);
        identifier.setName(rainStation.getStationName());
        insertSensor.setIdentifier(identifier);
    }

    private void setIdentification() {
        Identification identification = new Identification();
        Identifier name = new Identifier();
        identification.setIdentifierList(new Identifier[]{ name });
        name.setTerm( new Term("http://sensorml.com/ont/swe/property/ChineseName", "Chinese Name", rainStation.getStationName()));
        insertSensor.setIdentification(identification);
    }

    private void setCharacteristics() {
        
    }

    private void setCapabilities() {
        Capabilities[] capabilities = new Capabilities[1];
        capabilities[0] = new Capabilities();
        capabilities[0].setName("offerings");
        capabilities[0].setCapability(new Capability[]{new Capability()});
        capabilities[0].getCapability()[0].setName("offeringID");
        Text text = new Text();
        text.setDefinition("urn:ogc:def:identifier:OGC:offeringID");
        text.setLabel("offeringID");
        text.setValue(rainStation.getStationName());
        capabilities[0].getCapability()[0].setText(text);

        insertSensor.setCapabilities(capabilities);
    }

    private void setDocumentation() {
        Documentation documentation = new Documentation();
        documentation.setUrl("http://dfm.swcb.gov.tw/DebrisService/InformationService.asmx");
        insertSensor.setDocumentation(documentation);
    }

    private void setInput() {
        Input input = new Input();
        input.setName("Rainfall");
        input.setObservableProperty(new ObservableProperty());
        input.getObservableProperty().setDefinition("http://sensorml.com/ont/swe/property/Rainfall");
        insertSensor.setInput(new Input[]{input});
    }

    private void setOutput() {
        Output[] outputs = {new Output()};
        outputs[0].setName("Rainfall");
        outputs[0].setQuantity(new Quantity());
        outputs[0].getQuantity().setDefinition("http://sensorml.com/ont/swe/property/Rainfall");
        outputs[0].getQuantity().setLabel("Rain fall");
        outputs[0].getQuantity().setUom("mm");

        insertSensor.setOutput(outputs);
    }

    private void setObservableProperties() {
        ObservableProperty[] observableProperties = createObservableProperties("urn:ogc:def:phenomenon:OGC:1.0.30",
                new String[]{"rainfall_10min", "rainfall_1hr", "rainfall_3hr", "rainfall_6hr", "rainfall_1day", "rainfall_24hr", "rainfall_12hr" });
        insertSensor.setObservableProperties(observableProperties);
    }

    private ObservableProperty[] createObservableProperties(String prefix, String[] properties){
        ObservableProperty[] observableProperties = new ObservableProperty[properties.length];
        for( int i = 0; i < properties.length ; i++  ){
            observableProperties[i] = new ObservableProperty();
            observableProperties[i].setPrefix(prefix);
            observableProperties[i].setName(properties[i]);
        }
        return observableProperties;
    }

    private void setObservationTypes() {
        ObservationType[] observationTypes = {new ObservationType()};
        observationTypes[0].setName("http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement");
        insertSensor.setObservationTypes(observationTypes);
    }
}

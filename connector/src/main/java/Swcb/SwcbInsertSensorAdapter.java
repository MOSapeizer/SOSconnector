package Swcb;

import helper.InsertSensorML20Helper;
import insertSensorML20.*;

import java.util.LinkedList;

/**
 * Created by Zil on 2016/9/29.
 */
public class SwcbInsertSensorAdapter {

    private CctvJson profile;
    private InsertSensor insertSensor = new InsertSensor();
    private InsertSensorML20Helper helper = new InsertSensorML20Helper();
    private String prefix = "urn:ogc:object:feature:sensor:swcb";

    public SwcbInsertSensorAdapter(CctvJson profile){
        this.profile = profile;
    }

    private void before(){
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

    private void setIdentifier(){
        Identifier identifier = new Identifier();
        identifier.setPrefix(prefix);
        identifier.setName(profile.getCameraName());
        insertSensor.setIdentifier(identifier);
    }

    private void setIdentification(){
        Identification identification = new Identification();
        Identifier name1 = new Identifier();
        identification.setIdentifierList(new Identifier[]{ name1 });
        if( profile.getCameraName() != null )
            name1.setTerm( new Term("http://sensorml.com/ont/swe/property/ChineseName", "Camera Name", profile.getCameraName()));
        insertSensor.setIdentification(identification);
    }

    private void setCharacteristics(){
        Characteristic[] characteristics = new Characteristic[1];
        characteristics[0] = new Characteristic();
        characteristics[0].setName("Information");
        characteristics[0].setDataRecord(new DataRecord());
        characteristics[0].getDataRecord().setLabel("測站相關資訊");
        LinkedList<Field> fields = new LinkedList<Field>();
        fields.add(helper.getTextField("City", "縣市", profile.getCity()));
        fields.add(helper.getTextField("County", "行政區", profile.getCounty()));
        fields.add(helper.getTextField("Address", "架設或拍攝地點", profile.getAddress()));
        characteristics[0].getDataRecord().setField(fields.toArray(new Field[fields.size()]));
        insertSensor.setCharacteristics(characteristics);
    }

    private void setCapabilities(){
        Capabilities[] capabilities = new Capabilities[1];
        capabilities[0] = new Capabilities();
        capabilities[0].setName("offerings");
        capabilities[0].setCapability(new Capability[]{new Capability()});
        capabilities[0].getCapability()[0].setName("offeringID");
        Text text = new Text();
        text.setDefinition("urn:ogc:def:identifier:OGC:offeringID");
        text.setLabel("offeringID");
        text.setValue(profile.getCameraName());
        capabilities[0].getCapability()[0].setText(text);

        insertSensor.setCapabilities(capabilities);
    }

    private void setDocumentation(){
        Documentation documentation = new Documentation();
        documentation.setUrl("http://data.coa.gov.tw/Service/OpenData/DataFileService.aspx?UnitId=111");
        insertSensor.setDocumentation(documentation);
    }

    private void setInput(){
        Input input = new Input();
        input.setName("CCTV");
        input.setObservableProperty(new ObservableProperty());
        input.getObservableProperty().setDefinition("http://sensorml.com/ont/swe/property/CCTV");

        insertSensor.setInput(new Input[]{input});
    }

    private void setOutput(){
        Output[] outputs = {new Output()};
        outputs[0].setName("CCTV");
        outputs[0].setText(new Text());
        outputs[0].getText().setDefinition(prefix);
        insertSensor.setOutput(outputs);
    }

    private void setObservableProperties(){

        ObservableProperty[] observableProperties = {new ObservableProperty()};
        observableProperties[0].setPrefix("urn:ogc:def:phenomenon:OGC:1.0.30");
        observableProperties[0].setName("radiance");
        insertSensor.setObservableProperties(observableProperties);
    }

    private void setObservationTypes(){
        ObservationType[] observationTypes = {new ObservationType()};
        observationTypes[0].setName("http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_TextObservation");
        insertSensor.setObservationTypes(observationTypes);
    }
}

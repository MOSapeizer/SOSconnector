package insertSensorML20;

/**
 * Created by Zil on 2016/9/22.
 */
public class InsertSensor {
    private Identifier identifier;
    private Identification identification;
    private Characteristic[] characteristics;
    private Capabilities[] capabilities;
    private Documentation documentation;
    private Input[] input;
    private Output[] output;
    private ObservableProperty[] observableProperties;
    private ObservationType[] observationTypes;

    public InsertSensor(){

    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public ObservableProperty[] getObservableProperties() {
        return observableProperties;
    }

    public void setObservableProperties(ObservableProperty[] observableProperties) {
        this.observableProperties = observableProperties;
    }

    public ObservationType[] getObservationTypes() {
        return observationTypes;
    }

    public void setObservationTypes(ObservationType[] observationTypes) {
        this.observationTypes = observationTypes;
    }

    public Characteristic[] getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristic[] characteristics) {
        this.characteristics = characteristics;
    }

    public Capabilities[] getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capabilities[] capabilities) {
        this.capabilities = capabilities;
    }

    public Documentation getDocumentation() {
        return documentation;
    }

    public void setDocumentation(Documentation documentation) {
        this.documentation = documentation;
    }

    public Input[] getInput() {
        return input;
    }

    public void setInput(Input[] input) {
        this.input = input;
    }

    public Output[] getOutput() {
        return output;
    }

    public void setOutput(Output[] output) {
        this.output = output;
    }
}
package insertSensorML20;

/**
 * Created by Zil on 2016/9/22.
 */
public class InsertSensor {
    private Identifier identifier;
    private Identification identification;
    private Characteristic[] characteristics;
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
}
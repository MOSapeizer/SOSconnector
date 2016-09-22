package insertSensorML20;

/**
 * Created by Zil on 2016/9/23.
 */
public class DataRecord {

    private String label;
    private String definition;
    private Field[] field;

    public DataRecord() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Field[] getField() {
        return field;
    }

    public void setField(Field[] field) {
        this.field = field;
    }
}

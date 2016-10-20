package insertSensorML20;

/**
 * Created by Zil on 2016/9/22.
 */
public class Term {

    private String definition;
    private String label;
    private String value;

    public Term() {
    }

    public Term(String definition, String label, String value) {
        this.definition = definition;
        this.label = label;
        this.value = value;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

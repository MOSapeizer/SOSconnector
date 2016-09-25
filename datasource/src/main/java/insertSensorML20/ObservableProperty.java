package insertSensorML20;

/**
 * Created by Zil on 2016/9/22.
 */
public class ObservableProperty {

    private String definition;
    private String prefix;
    private String name;

    public ObservableProperty(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}

package insertSensorML20;

/**
 * Created by Zil on 2016/9/24.
 */
public class Input {

    private String name;
    private ObservableProperty observableProperty;

    public ObservableProperty getObservableProperty() {
        return observableProperty;
    }

    public void setObservableProperty(ObservableProperty observableProperty) {
        this.observableProperty = observableProperty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

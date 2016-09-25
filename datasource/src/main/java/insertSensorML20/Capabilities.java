package insertSensorML20;

/**
 * Created by Zil on 2016/9/23.
 */
public class Capabilities {

    private String name;
    private Capability[] capability;

    public Capabilities(){

    }

    public Capability[] getCapability() {
        return capability;
    }

    public void setCapability(Capability[] capability) {
        this.capability = capability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

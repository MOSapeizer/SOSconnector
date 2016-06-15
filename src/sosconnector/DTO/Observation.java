package sosconnector.DTO;

/**
 * Created by zil on 2016/6/15.
 */
public class Observation {

    private String name;
    private String type;
    private String unit;
    private String value;
    private String timestamp;

    public Observation(String name, String type, String unit, String value, String timestamp) {
        this.name = name;
        this.type = type;
        this.unit = unit;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getUnit() {
        return unit;
    }

    public String getValue() {
        return value;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

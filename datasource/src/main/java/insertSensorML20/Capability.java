package insertSensorML20;

/**
 * Created by Zil on 2016/9/23.
 */
public class Capability {

    private String name;
    private DataRecord dataRecord;
    private Text text;

    public Capability(){

    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public DataRecord getDataRecord() {
        return dataRecord;
    }

    public void setDataRecord(DataRecord dataRecord) {
        this.dataRecord = dataRecord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

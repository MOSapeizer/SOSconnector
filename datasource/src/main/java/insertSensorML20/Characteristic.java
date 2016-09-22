package insertSensorML20;

/**
 * Created by Zil on 2016/9/23.
 */
public class Characteristic {

    private String name;
    private DataRecord dataRecord;

    public Characteristic() {
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

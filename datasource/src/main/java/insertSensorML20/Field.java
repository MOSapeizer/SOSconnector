package insertSensorML20;

/**
 * Created by Zil on 2016/9/23.
 */
public class Field {

    private String name;
    private Text text;
    private DataRecord dataRecord;
    private Quantity quantity;
    private Boolean aBoolean;

    public Field(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
}

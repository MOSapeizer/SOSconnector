package insertSensorML20;

/**
 * Created by Zil on 2016/9/24.
 */
public class Output {
    private String name;
    private Quantity quantity;
    private Text text;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}

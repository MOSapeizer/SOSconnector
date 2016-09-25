package helper;

import insertSensorML20.Field;
import insertSensorML20.Quantity;
import insertSensorML20.Text;

/**
 * Created by Zil on 2016/9/25.
 */
public class InsertSensorML20Helper {

    public Field getTextField(String name, String label, String value){
        if( value == null || value.equals("") )
            return null;

        Field field = new Field();
        Text text = new Text();
        text.setLabel(label);
        text.setValue(value);
        field.setName(name);
        field.setText(text);
        return field;
    }

    public Field getBooleanField(String name, String aBoolean){
        Field field = new Field();
        field.setName(name);
        field.setaBoolean(Boolean.getBoolean(aBoolean));
        return field;
    }

    public Field getQuantityField(String name, String q_definition, String q_code, String q_value){
        if( q_value == null || q_value.equals("") )
            return null;

        Field field = new Field();
        Quantity quantity = new Quantity();
        quantity.setDefinition(q_definition);
        quantity.setUom(q_code);
        quantity.setValue(q_value);
        field.setName(name);
        field.setQuantity(quantity);
        return field;
    }
}

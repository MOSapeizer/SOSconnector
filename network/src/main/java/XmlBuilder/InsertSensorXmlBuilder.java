package XmlBuilder;

import Template.InsertSensorTemplate;
import insertSensorML20.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Zil on 2016/9/22.
 */
public class InsertSensorXmlBuilder extends XmlBuilder {
    private InsertSensor insertSensor;

    public InsertSensorXmlBuilder(InsertSensor insertSensor) throws IOException, SAXException, ParserConfigurationException {
        super(InsertSensorTemplate.base);
        this.insertSensor = insertSensor;
    }

    public void map(){
        append("sml:PhysicalComponent");
        if( insertSensor.getIdentifier() != null )
            setIdentifier();

        if( insertSensor.getIdentification() != null ){
            if( insertSensor.getIdentification().getIdentifierList().length > 0)
                setIdentification();
        }

        if( insertSensor.getCharacteristics() != null ){
            setCharacteristics();
        }

        if( insertSensor.getObservableProperties() != null )
            setObservableProperty();

        if( insertSensor.getObservationTypes() != null )
            setObservationType();

        up();
    }

    private void setIdentifier(){
        Identifier identifier = insertSensor.getIdentifier();
        append("gml:identifier").attribute("codeSpace", "uniqueID")
            .text(identifier.getPrefix() + ":" + identifier.getName()).up();
    }

    private void setIdentification(){
        Identification identification = insertSensor.getIdentification();
        append("sml:identification")
            .append("sml:IdentifierList");
            for( Identifier identifier : identification.getIdentifierList() ){
                Term term = identifier.getTerm();
                append("sml:identifier")
                    .append("sml:Term").attribute("definition", term.getDefinition())
                        .append("sml:label").text(term.getLabel()).up()
                        .append("sml:value").text(term.getValue()).up()
                .up();
            }
            up();
        up();
    }


    private void setCharacteristics() {
        Characteristic[] characteristics = insertSensor.getCharacteristics();
        append("sml:characteristics").attribute("name", "generalProperties")
            .append("sml:CharacteristicList");
                for( Characteristic characteristic : characteristics ){
                    append("sml:characteristic").attribute("name", characteristic.getName());
                        DataRecord dataRecord = characteristic.getDataRecord();
                        setDataRecord(dataRecord);
                    up();
                }
            up()
        .up();
    }

    private void setDataRecord(DataRecord dataRecord){
        append("swe:DataRecord");
        if( dataRecord.getDefinition() != null )
            attribute("definition", dataRecord.getDefinition());
        if( dataRecord.getLabel() != null )
            append("swe:label").text(dataRecord.getLabel());
        if( dataRecord.getField().length > 0 ) {
            setField(dataRecord.getField());
        }
        up();
    }

    private void setField(Field[] fields){
        for( Field field: fields ) {
            append("swe:field").attribute("name", field.getName());
            if( field.getText() != null ){
                Text text = field.getText();
                append("swe:Text")
                    .append("swe:description").text(text.getDescription()).up()
                    .append("swe:value").text(text.getValue()).up()
                .up();
            } else if( field.getDataRecord() != null ){
                setDataRecord( field.getDataRecord() );
            } else if( field.getQuantity() != null ){
                Quantity quantity = field.getQuantity();
                append("swe:Quantity");
                    if( quantity.getDefinition() != null )
                        attribute("definition", quantity.getDefinition());
                    append("swe:uom").attribute("code", quantity.getUom()).up();
                    append("swe:value").text(quantity.getValue()).up();
                up();
            } else if( field.getaBoolean() != null ){
                append("swe:Boolean")
                    .append("swe:value").text(field.getaBoolean().toString()).up()
                .up();
            }
            up();
        }
    }

    private void setObservableProperty(){
        for( ObservableProperty observableProperty : insertSensor.getObservableProperties() ){
            append("swes:observableProperty")
                .text(observableProperty.getPrefix() + ":" + observableProperty.getName())
            .up();
        }
    }

    private void setObservationType(){
        append("swes:metadata")
            .append("sos:SosInsertionMetadata");
                for( ObservationType observationType : insertSensor.getObservationTypes() ) {
                    append("sos:observationType").text("")
                    .up();
                }
            up()
        .up();
    }
}

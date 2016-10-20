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
        append("swes:procedureDescription")
            .append("sml:PhysicalComponent").attribute("gml:id", "sensor1");
            if( insertSensor.getIdentifier() != null )
                setIdentifier(insertSensor.getIdentifier());

            if( insertSensor.getIdentification() != null ){
                if( insertSensor.getIdentification().getIdentifierList().length > 0)
                    setIdentification(insertSensor.getIdentification());
            }

            if( insertSensor.getCharacteristics() != null ){
                setCharacteristics(insertSensor.getCharacteristics());
            }

            if( insertSensor.getCapabilities() != null )
                setCapabilities(insertSensor.getCapabilities());

            if( insertSensor.getDocumentation() != null )
                setDocumentation(insertSensor.getDocumentation());

            if( insertSensor.getInput() != null )
                setInput(insertSensor.getInput());

            if( insertSensor.getOutput() != null )
                setOutput(insertSensor.getOutput());
            up();

        up();
        if( insertSensor.getObservableProperties() != null )
            setObservableProperty(insertSensor.getObservableProperties());

        if( insertSensor.getObservationTypes() != null )
            setObservationType(insertSensor.getObservationTypes());
    }

    private void setOutput(Output[] outputs) {
        append("sml:outputs")
            .append("sml:OutputList");
            for( Output output : outputs ){
                append("sml:output").attribute("name", output.getName());
                    if( output.getText() != null ) {
                        setText(output.getText());
                    } else if( output.getQuantity() != null ) {
                        setQuantity(output.getQuantity());
                    }
                up();
            }
            up();
        up();
    }

    private void setInput(Input[] inputs) {
        append("sml:inputs")
            .append("sml:InputList");
            for( Input input : inputs ){
                append("sml:input").attribute("name", input.getName());
                    append("sml:ObservableProperty").attribute("definition", input.getObservableProperty().getDefinition())
                    .up();
                up();
            }
            up();
        up();
    }

    public void setCapabilities(Capabilities[] capabilityList) {
        for( Capabilities capabilities : capabilityList ){
            append("sml:capabilities").attribute("name", capabilities.getName());
                append("sml:CapabilityList");
                    for( Capability capability : capabilities.getCapability() )
                        setCapability( capability );
                up();
            up();
        }

    }

    public void setDocumentation(Documentation documentation){
        append("sml:documentation")
            .append("sml:DocumentList")
                .append("sml:document").attribute("xlink:arcrole", "http://sensorml.com/ont/swe/role/UserManual")
                    .append("gmd:CI_OnlineResource")
                        .append("gmd:linkage")
                            .append("gmd:URL").text(documentation.getUrl())
                            .up()
                        .up()
                    .up()
                .up()
            .up()
        .up();
    }

    public void setCapability(Capability capability){
        append("sml:capability").attribute("name", capability.getName());
            if( capability.getDataRecord() != null ){
                setDataRecord(capability.getDataRecord());
            } else if( capability.getText() != null ){
                setText( capability.getText() );
            }
        up();
    }

    public void setIdentifier(Identifier identifier){
        append("gml:identifier").attribute("codeSpace", "uniqueID")
            .text(identifier.getPrefix() + ":" + identifier.getName()).up();
    }

    public void setIdentification(Identification identification){
        append("sml:identification")
            .append("sml:IdentifierList");
            for( Identifier identifier : identification.getIdentifierList() ){
                Term term = identifier.getTerm();
                append("sml:identifier")
                    .append("sml:Term").attribute("definition", term.getDefinition())
                        .append("sml:label").text(term.getLabel()).up()
                        .append("sml:value").text(term.getValue()).up()
                    .up()
                .up();
            }
            up();
        up();
    }


    public void setCharacteristics(Characteristic[] characteristics) {
        append("sml:characteristics").attribute("name", "generalProperties")
            .append("sml:CharacteristicList");
                for( Characteristic characteristic : characteristics ){
                    if( characteristic.getDataRecord() == null )
                        continue;
                    append("sml:characteristic").attribute("name", characteristic.getName());
                        DataRecord dataRecord = characteristic.getDataRecord();
                        setDataRecord(dataRecord);
                    up();
                }
            up()
        .up();
    }

    private void setDataRecord(DataRecord dataRecord){
        if( dataRecord == null )
            return;

        append("swe:DataRecord");
        if( dataRecord.getDefinition() != null )
            attribute("definition", dataRecord.getDefinition());
        if( dataRecord.getLabel() != null )
            append("swe:label").text(dataRecord.getLabel()).up();
        if( dataRecord.getField().length > 0 ) {
            setField(dataRecord.getField());
        }
        up();
    }

    private void setField(Field[] fields){
        for( Field field: fields ) {
            if( field == null )
                continue;

            append("swe:field").attribute("name", field.getName());
            if( field.getText() != null ){
                Text text = field.getText();
                setText(text);
            } else if( field.getDataRecord() != null ){
                setDataRecord( field.getDataRecord() );
            } else if( field.getQuantity() != null ){
                setQuantity(field.getQuantity());
            } else if( field.getaBoolean() != null ){
                append("swe:Boolean")
                    .append("swe:value").text(field.getaBoolean().toString()).up()
                .up();
            }
            up();
        }
    }

    private void setQuantity(Quantity quantity){
        append("swe:Quantity");
            if( quantity.getDefinition() != null )
                attribute("definition", quantity.getDefinition());

            if( quantity.getLabel() != null )
                append("swe:label").text(quantity.getLabel()).up();

            append("swe:uom").attribute("code", quantity.getUom()).up();

            if( quantity.getValue() != null )
                append("swe:value").text(quantity.getValue()).up();
        up();
    }

    private void setText(Text text){
        append("swe:Text");
            if( text.getDescription() != null ) {
                append("swe:description").text(text.getDescription()).up();
            } else if( text.getLabel() != null ) {
                append("swe:label").text(text.getLabel()).up();
            }
            if( text.getDefinition() != null )
                attribute("definition", text.getDefinition());

            if( text.getValue() != null )
                append("swe:value").text(text.getValue()).up();
        up();
    }

    public void setObservableProperty(ObservableProperty[] observableProperties){
        for( ObservableProperty observableProperty : observableProperties ){
            append("swes:observableProperty");
                if( observableProperty.getDefinition() != null )
                    attribute("definition", observableProperty.getDefinition());
                if( observableProperty.getName() != null )
                    text(observableProperty.getPrefix() + ":" + observableProperty.getName());
            up();
        }
    }

    public void setObservationType(ObservationType[] observationTypes){
        append("swes:metadata")
            .append("sos:SosInsertionMetadata");
                for( ObservationType observationType : observationTypes ) {
                    append("sos:observationType").text(observationType.getName())
                    .up();
                }
            up()
        .up();
    }
}

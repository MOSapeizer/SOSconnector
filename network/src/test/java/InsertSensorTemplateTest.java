import XmlBuilder.InsertSensorXmlBuilder;
import insertSensorML20.Identifier;
import insertSensorML20.InsertSensor;
import junit.framework.TestCase;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Zil on 2016/9/22.
 */
public class InsertSensorTemplateTest extends TestCase {

    private InsertSensor insertSensor;

    @Override
    protected void setUp() throws Exception {
        insertSensor = new InsertSensor();
        Identifier identifier = new Identifier();
        identifier.setPrefix("prefix");
        identifier.setName("name");
        insertSensor.setIdentifier(identifier);
    }

    public void testInsertSenser() throws ParserConfigurationException, SAXException, IOException {
        InsertSensorXmlBuilder insertSensorXmlBuilder = new InsertSensorXmlBuilder(insertSensor);
        insertSensorXmlBuilder.map();
        System.out.println(insertSensorXmlBuilder.toXML());
    }
}

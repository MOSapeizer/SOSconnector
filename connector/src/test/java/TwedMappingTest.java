import Twed.TaiwanWaterExchangingData;
import Twed.TwedInsertSensorAdpater;
import XmlBuilder.InsertSensorXmlBuilder;
import insertSensorML20.InsertSensor;
import junit.framework.TestCase;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Zil on 2016/9/25.
 */
public class TwedMappingTest extends TestCase {

    Unmarshaller unmarshaller;
    TaiwanWaterExchangingData object;

    @Override
    protected void setUp() throws Exception {
        File file = new File("./src/main/resources/Sensor.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(TaiwanWaterExchangingData.class);
        unmarshaller = jaxbContext.createUnmarshaller();
        object = (TaiwanWaterExchangingData) unmarshaller.unmarshal(file);

    }

    public void testMappingBasic() throws ParserConfigurationException, SAXException, IOException {
        TwedInsertSensorAdpater adpater = new TwedInsertSensorAdpater(object.getHydrologyRiverClass().getRiverStageObservatoryProfile()[0]);
        InsertSensor insertSensor = adpater.getInsertSensor();
        InsertSensorXmlBuilder builder = new InsertSensorXmlBuilder(insertSensor);
        builder.map();
        System.out.println(builder.toXML());

    }

    public void testMappingObservation(){
//        System.out.println(InsertObservationTemplate.sample_output());
    }
}

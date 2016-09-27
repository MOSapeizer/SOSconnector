package Request;

import Twed.WaterLevelObservation;
import XmlBuilder.InsertObservationXmlBuilder;
import XmlBuilder.InsertSensorXmlBuilder;
import insertSensorML20.InsertSensor;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by zil on 2016/3/30.
 */
public class SosRequest extends Request {

    public SosRequest(String url) throws IOException {
        super(url);
    }

    public void insertSensor(InsertSensor insertSensor) throws ParserConfigurationException, SAXException, IOException {
        InsertSensorXmlBuilder builder = new InsertSensorXmlBuilder(insertSensor);
        builder.map();
        System.out.println(builder.toXML());
        String response = post(builder.toXML());
        System.out.println(response);
    }

    public void insertObservation(WaterLevelObservation observation) throws IOException, SAXException, ParserConfigurationException {
        InsertObservationXmlBuilder builder = new InsertObservationXmlBuilder(observation);
        System.out.println(builder.toXML());
        String response = post(builder.toXML());
        System.out.println(response);
    }

}

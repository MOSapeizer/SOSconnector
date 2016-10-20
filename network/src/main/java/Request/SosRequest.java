package Request;

import Swcb.CctvJson;
import Swcb.Row;
import Twed.WaterLevelObservation;
import XmlBuilder.InsertObservationXmlBuilder;
import XmlBuilder.InsertSensorXmlBuilder;
import insertSensorML20.InsertSensor;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

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
//        if( response.contains("Exception") )
//            System.out.println(response);
        System.out.println(response);
    }

    public void insertObservation(Row observation) throws IOException, SAXException, ParserConfigurationException, ParseException {
        InsertObservationXmlBuilder builder = new InsertObservationXmlBuilder(observation);
        System.out.println(builder.toXML());
        String response = post(builder.toXML());
//        if( response.contains("Exception") )
//            System.out.println(response);
        System.out.println(response);
    }

    public void insertObservation(CctvJson observation) throws IOException, SAXException, ParserConfigurationException {
        InsertObservationXmlBuilder builder = new InsertObservationXmlBuilder(observation);
        System.out.println(builder.toXML());
        String response = post(builder.toXML());
        System.out.println(response);
    }

    public void printInsertSensorML(InsertSensor insertSensor) throws ParserConfigurationException, SAXException, IOException {
        InsertSensorXmlBuilder builder = new InsertSensorXmlBuilder(insertSensor);
        builder.map();
        System.out.println(builder.toXML());
    }

    public void printInsertObservation(CctvJson observation) throws ParserConfigurationException, SAXException, IOException {
        InsertObservationXmlBuilder builder = new InsertObservationXmlBuilder(observation);
        System.out.println(builder.toXML());
    }

}

package XmlBuilder;

import Template.InsertObservationTemplate;
import Twed.WaterLevelObservation;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Zil on 2016/9/26.
 */
public class InsertObservationXmlBuilder extends XmlBuilder {
    private WaterLevelObservation observation;

    public InsertObservationXmlBuilder(WaterLevelObservation observation) throws ParserConfigurationException, IOException, SAXException {
        super(InsertObservationTemplate.sample_output(
                observation.getOffering(),
                observation.getType(),
                observation.getTimePosition(),
                observation.getProcedure(),
                observation.getObservedProperty(),
                observation.getFoiID(),
                observation.getFoiName(),
                observation.getPosition(),
                observation.getResultType(),
                observation.getUom(),
                observation.getResult()
        ));
    }




}

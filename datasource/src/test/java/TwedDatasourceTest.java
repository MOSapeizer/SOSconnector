import Twed.TaiwanWaterExchangingData;
import junit.framework.TestCase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Zil on 2016/9/23.
 */
public class TwedDatasourceTest extends TestCase {

    Unmarshaller unmarshaller;
    TaiwanWaterExchangingData object;

    @Override
    protected void setUp() throws Exception {
        File file = new File("./src/main/resources/Sensor.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(TaiwanWaterExchangingData.class);
        unmarshaller = jaxbContext.createUnmarshaller();
        object = (TaiwanWaterExchangingData) unmarshaller.unmarshal(file);
    }

    public void testUnmarshall() throws JAXBException {
        double[] latLon = object.getHydrologyRiverClass().getRiverStageObservatoryProfile()[0].getLocationByTWD67().getLatLon();
        System.out.println(latLon[0] + " " + latLon[1]);
    }

}

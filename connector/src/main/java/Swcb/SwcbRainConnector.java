package Swcb;

import Connector.Connector;
import Request.Request;
import insertSensorML20.InsertSensor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Zil on 2016/10/18.
 */
public class SwcbRainConnector extends Connector {

    private String sensor = "http://dfm.swcb.gov.tw/DebrisService/InformationService.asmx/GetStation";
    private String observation = "http://dfm.swcb.gov.tw/DebrisService/InformationService.asmx/GetAllStationRain";
    private DataTable rainStation;
    private DataTable rainfall;

    public SwcbRainConnector(String sosUrl) throws IOException {
        super(sosUrl);
    }

    protected void setUp() throws Exception {
        rainStation = (DataTable) unmarshallXmlResourceToPOJO(sensor);
        rainfall = (DataTable) unmarshallXmlResourceToPOJO(observation);
//        rainStation = (DataTable) unmarshallXmlResourceToPOJO("GetStation.xml");
//        rainfall = (DataTable) unmarshallXmlResourceToPOJO("GetAllStationRain.xml");

    }

    private Object unmarshallXmlResourceToPOJO(String url) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(DataTable.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        unmarshaller.setEventHandler(
//                new ValidationEventHandler() {
//                    public boolean handleEvent(ValidationEvent event ) {
//                        throw new RuntimeException(event.getMessage(),
//                                event.getLinkedException());
//                    }
//                });
        Request request = new Request(url);
        String sensor = request.post("");
        return unmarshaller.unmarshal(new StreamSource( new StringReader(sensor)));
    }

    public void run() {
        try {
            setUp();
            for(Row station : rainStation.getRows()){
                SwcbRainInsertSensorAdapter adapter = new SwcbRainInsertSensorAdapter(station);
                InsertSensor insertSensor = adapter.getInsertSensor();
                sosRequest.insertSensor(insertSensor);
            }

            for( Row rain : rainfall.getRows() ){
                sosRequest.insertObservation(rain);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

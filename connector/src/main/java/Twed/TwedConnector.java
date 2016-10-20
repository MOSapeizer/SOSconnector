package Twed;

import Connector.Connector;
import Request.Request;
import insertSensorML20.InsertSensor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

/**
 * Created by Zil on 2016/9/26.
 */
public class TwedConnector extends Connector {

    private Unmarshaller unmarshaller;
    private TaiwanWaterExchangingData object;
    private TaiwanWaterExchangingData object2;
    private String sensor = "http://data.wra.gov.tw/Service/OpenData.aspx?id=28E06316-FE39-40E2-8C35-7BF070FD8697&format=xml";
    private String observation = "http://data.wra.gov.tw/Service/OpenData.aspx?id=2D09DB8B-6A1B-485E-88B5-923A462F475C&format=xml";

    public TwedConnector(String sosUrl) throws IOException {
        super(sosUrl);
    }

    protected void setUp() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(TaiwanWaterExchangingData.class);
        unmarshaller = jaxbContext.createUnmarshaller();
        Request request = new Request(sensor);
        String sensor = request.get();
        Request request2 = new Request(observation);
        String obserBuffer = request2.get();
        object = (TaiwanWaterExchangingData) unmarshaller.unmarshal(new StreamSource( new StringReader(sensor)));
        object2 = (TaiwanWaterExchangingData) unmarshaller.unmarshal(new StreamSource( new StringReader(obserBuffer)));
    }

    public void run() {
        try {
            setUp();
            RiverStageObservatoryProfile[] riverStageObservatoryProfile = object.getHydrologyRiverClass().getRiverStageObservatoryProfile();
            RealtimeWaterLevel[] realtimeWaterLevel = object2.getHydrologyRiverClass().getRealtimeWaterLevel();
            HashMap<String, RiverStageObservatoryProfile> offeringHash = new HashMap<String, RiverStageObservatoryProfile>();

            for( RiverStageObservatoryProfile profile : riverStageObservatoryProfile ){
                offeringHash.put(profile.getBasinIdentifier().trim(), profile);
                TwedInsertSensorAdapter adpater = new TwedInsertSensorAdapter(profile);
                InsertSensor insertSensor = adpater.getInsertSensor();
                sosRequest.insertSensor(insertSensor);
            }

            for( RealtimeWaterLevel waterlevel : realtimeWaterLevel ) {
                RiverStageObservatoryProfile profile = offeringHash.get(waterlevel.getStationIdentifier());
                WaterLevelObservation waterLevelObservation = new WaterLevelObservation(profile, waterlevel);
                sosRequest.insertObservation(waterLevelObservation);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

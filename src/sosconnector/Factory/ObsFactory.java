package sosconnector.Factory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import sosconnector.DBManager;
import sosconnector.DTO.EpaDTO;
import sosconnector.ObservationXML.EpaXML;

/**
 * Created by zil on 2016/4/1.
 */
public class ObsFactory extends ResponseFactory<EpaDTO> {

    public ObsFactory(String source) {
        super(source);
    }

    @Override
    public Boolean whichIsRedundant(EpaDTO obj) {
        String siteName = obj.getSiteName();
        String publishTime = obj.getPublishTime();
        return (DBManager.getInstance().ifReadingRedundant("epa_aqx_reading", siteName, publishTime) != -1);
    }

    @Override
    public void initialize() {
        setDataName("Data");
    }

    @Override
    public EpaDTO operateNode(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            return new EpaDTO( (Element) node );
        }
        return null;
    }

    @Override
    public void finalManipulate(EpaDTO obj) {
        DBManager.getInstance().insertAQX_epa( obj );
        sendInsertObsRequest(epaURL, new EpaXML( obj ).getInsertObservationXML());
    }

    public void sendInsertObsRequest(String url, String requestBody){

        String response = sendInsertRequest(url, requestBody);
        writeToDocumnet( response );
    }

    @Override
    public String getXML(String name) {
        return null;
    }


}

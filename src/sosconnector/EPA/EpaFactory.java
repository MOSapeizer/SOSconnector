package sosconnector.EPA;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import sosconnector.DBManager;
import sosconnector.DTO.EpaDTO;
import sosconnector.Factory.ResponseFactory;
import sosconnector.ObservationXML.EpaXML;

/**
 * Created by zil on 2016/4/1.
 */
public class EpaFactory extends ResponseFactory<EpaDTO> {

    public EpaFactory(String source) {
        super(source);
    }

    @Override
    public void initialize() {
        setFilterNodeName("Data");
    }

    @Override
    public EpaDTO operateFilteredNode(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            return new EpaDTO( (Element) node );
        }
        return null;
    }

    @Override
    public Boolean whichFilteredNodeIsRedundant(EpaDTO epa) {
        String siteName = epa.getSiteName();
        String publishTime = epa.getPublishTime();
        return (DBManager.getInstance().ifReadingRedundant("epa_aqx_reading", siteName, publishTime) != -1);
    }

    @Override
    public void finalManipulate(EpaDTO epa) {
        DBManager.getInstance().insertAQX_epa( epa );
        sendInsertObsRequest(epaURL, new EpaXML( epa ).getInsertObservationXML());
    }

    public void sendInsertObsRequest(String url, String requestBody){
        String response = sendInsertRequest(url, requestBody);
        writeToDocumnet( response );
    }

}

package sosconnector.EPA;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import sosconnector.DBManager;
import sosconnector.Factory.ResponseFactory;
import sosconnector.ObservationXML.EpaXML;
import sosconnector.Request.InsertRequest;
import sosconnector.DTO.EpaSiteDTO;

/**
 * Created by zil on 2016/4/1.
 */
public class EpaSiteFactory extends ResponseFactory<EpaSiteDTO> {

    public EpaSiteFactory(String source ){
        super(source);
    }

    @Override
    public void initialize() {
        setFilterNodeName("Data");
    }

    @Override
    public EpaSiteDTO operateFilteredNode(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            return new EpaSiteDTO((Element) node);
        }
        return null;
    }

    @Override
    public Boolean whichFilteredNodeIsRedundant(EpaSiteDTO site) {
        String siteName = site.getSiteName();
        return siteIsDuplicate(siteName);
    }

    private Boolean siteIsDuplicate(String siteName){
        return DBManager.getInstance().ifStationRedundant("epa_aqx_station", siteName) != -1;
    }

    @Override
    public void finalManipulate(EpaSiteDTO site) {
        String siteName = site.getSiteName();
        DBManager.getInstance().insertStation_epa_aqx(site);
        sendInsertRequestWithPayload(siteName, epaURL);
    }

    private void sendInsertRequestWithPayload(String siteName, String url) {
        String insertSensorXml = InsertRequest.getInsertSensorXML(new EpaXML().getInsertSensorXml(siteName));
        String response = sendInsertRequest(url, insertSensorXml);
        writeToDocumnet( response );
    }
}
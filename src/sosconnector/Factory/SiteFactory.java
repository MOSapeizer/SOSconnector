package sosconnector.Factory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import sosconnector.DBManager;
import sosconnector.Request.InsertRequest;
import sosconnector.DTO.EpaSiteDTO;

/**
 * Created by zil on 2016/4/1.
 */
public class SiteFactory extends ResponseFactory<EpaSiteDTO> {

    public SiteFactory( String source ){
        super(source);
    }

    @Override
    public Boolean whichIsRedundant(EpaSiteDTO site) {
        String siteName = site.getSiteName();
        return siteIsDuplicate(siteName);
    }

    public Boolean siteIsDuplicate(String siteName){
        return DBManager.getInstance().ifStationRedundant("epa_aqx_station", siteName) != -1;
    }

    @Override
    public void initialize() {
        setDataName("Data");
    }

    @Override
    public EpaSiteDTO operateNode(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            return new EpaSiteDTO((Element) node);
        }
        return null;
    }

    @Override
    public void finalManipulate(EpaSiteDTO site) {
        String siteName = site.getSiteName();
        DBManager.getInstance().insertStation_epa_aqx(site);
        sendInsertRequestWithPayload(siteName, epaURL);
    }

    @Override
    public String getXML(String name) {
        return InsertRequest.getInsertSensorXml( name );
    }
}

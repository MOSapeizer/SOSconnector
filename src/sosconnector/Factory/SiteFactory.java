package sosconnector.Factory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import sosconnector.DBManager;
import sosconnector.Request.InsertRequest;
import sosconnector.DTO.SiteDTO;

/**
 * Created by zil on 2016/4/1.
 */
public class SiteFactory extends ResponseFactory<SiteDTO> {

    public SiteFactory( String source ){
        super(source);
    }

    @Override
    public Boolean whichIsRedundant(SiteDTO site) {
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
    public SiteDTO operateNode(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            return new SiteDTO((Element) node);
        }
        return null;
    }

    @Override
    public void finalManipulate(SiteDTO site) {
        String siteName = site.getSiteName();
        DBManager.getInstance().insertStation_epa_aqx(site);
        sendInsertRequestWithPayload(siteName, epaURL);
    }

    @Override
    public String getXML(String name) {
        return InsertRequest.getInsertSensorXml( name );
    }
}

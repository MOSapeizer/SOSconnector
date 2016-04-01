package sosconnector.Factory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import sosconnector.DBManager;
import sosconnector.Request.InsertRequest;
import sosconnector.DTO.SiteDTO;

/**
 * Created by zil on 2016/4/1.
 */
public class SiteFactory extends SendInsertRequest<SiteDTO> {

    public SiteFactory( String source ){
        super(source);
    }

    @Override
    public Boolean redundantOrNot(SiteDTO site) {
        String siteName = site.getSiteName();
        return (DBManager.getInstance().ifStationRedundant("epa_aqx_station", siteName) == -1);
    }

    @Override
    public SiteDTO make(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            return new SiteDTO((Element) node);
        }
        return null;
    }

    @Override
    public void manipulate(SiteDTO site) {
        String siteName = site.getSiteName();
        DBManager.getInstance().insertStation_epa_aqx(site);
        sendInsertRequest(siteName, epaURL);
    }

    @Override
    public String getXML(String name) {
        return InsertRequest.getInsertSensorXml( name );
    }
}

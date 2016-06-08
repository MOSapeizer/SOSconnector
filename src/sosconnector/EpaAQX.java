package sosconnector;

import sosconnector.Adapter.SosFactory;
import sosconnector.DAO.EpaAqxDAO;
import sosconnector.DTO.EpaAqxSiteDTO;

import java.util.TimerTask;

/**
 * Created by zil on 2016/6/2.
 */
public class EpaAQX extends TimerTask {
    private final String url = "http://opendata2.epa.gov.tw/AQX.xml";
    private final String site_url = "http://opendata.epa.gov.tw/ws/Data/AQXSite/?$orderby=SiteName&$skip=0&format=xml";
    private final String sitePath = "xml/EpaAqxSite.xml";
    private final String dataPath = "xml/EpaAqx.xml";
    private final String service = "http://localhost:8080/epa-apx/service";

    @Override
    public void run() {
        EpaAqxDAO epa = new EpaAqxDAO(url, dataPath);
        epa.setSiteResource(site_url, sitePath, EpaAqxSiteDTO.class);
        new SosFactory<>( epa, service ).work();
    }
}

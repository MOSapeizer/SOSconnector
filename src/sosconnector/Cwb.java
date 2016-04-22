package sosconnector;

import sosconnector.Adapter.SosFactory;
import sosconnector.DAO.CwbSeaDAO;
import java.util.TimerTask;

/**
 * Created by zil on 2016/4/22.
 */
public class Cwb extends TimerTask {

    private final static String url = "http://opendata.cwb.gov.tw/govdownload?dataid=O-A0019-001&authorizationkey=rdec-key-123-45678-011121314";
    private final static String filePath = "src/sosconnector/GovConfigure/CwbSea.xml";
    private final static String service = "http://localhost:8080/CwbSea/service";

    @Override
    public void run() {
        new SosFactory<>( new CwbSeaDAO(url, filePath), service );
    }

}

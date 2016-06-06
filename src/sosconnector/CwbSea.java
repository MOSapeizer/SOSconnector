package sosconnector;

import sosconnector.Adapter.SosFactory;
import sosconnector.DAO.CwbSeaDAO;
import java.util.TimerTask;

/**
 * Created by zil on 2016/4/22.
 */
public class CwbSea extends TimerTask {

    private final static String url = "http://opendata.cwb.gov.tw/govdownload?dataid=O-A0019-001&authorizationkey=rdec-key-123-45678-011121314";
    private final static String filePath = "xml/CwbSea.xml";
    private final static String service = "http://localhost:8080/cwb_sea/service";

    @Override
    public void run() {
        CwbSeaDAO cwb = new CwbSeaDAO(url, filePath);
        new SosFactory<>( cwb , service ).work();
    }

}
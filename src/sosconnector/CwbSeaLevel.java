package sosconnector;

import sosconnector.Adapter.SosFactory;
import sosconnector.DAO.CwbSeaLevelDAO;

import java.util.TimerTask;

/**
 * Created by zil on 2016/6/6.
 */
public class CwbSeaLevel extends TimerTask {

    private final static String url = "http://opendata.cwb.gov.tw/govdownload?dataid=O-A0017-001&authorizationkey=rdec-key-123-45678-011121314";
    private final static String filePath = "src/sosconnector/GovConfigure/CwbSeaLevel.xml";
    private final static String service = "http://localhost:8080/cwb_seaLevel/service";

    @Override
    public void run() {
        CwbSeaLevelDAO cwb = new CwbSeaLevelDAO(url, filePath);
        new SosFactory<>( cwb , service ).work();
    }

}
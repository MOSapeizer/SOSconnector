package sosconnector;

import sosconnector.Adapter.SosFactory;
import sosconnector.DAO.SwcbDAO;
import java.util.TimerTask;

/**
 * Created by zil on 2016/4/29.
 */
public class Swcb extends TimerTask {
    private final String url = "http://data.coa.gov.tw/Service/OpenData/DataFileService.aspx?UnitId=111";
    private final String service = "http://localhost:8080/swcb_cctv/service";

    @Override
    public void run() {
        SwcbDAO swcb = new SwcbDAO(url);
        new SosFactory<>( swcb, service ).work();
    }
}
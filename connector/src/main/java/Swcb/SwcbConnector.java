package Swcb;

import Connector.Connector;
import Request.JsonReaderCustom;
import insertSensorML20.InsertSensor;
import org.json.JSONArray;

import java.io.IOException;

/**
 * Created by Zil on 2016/9/29.
 */
public class SwcbConnector extends Connector {

    private String source = "http://data.coa.gov.tw/Service/OpenData/DataFileService.aspx?UnitId=111";
    private CctvJson[] cctvs;

    public SwcbConnector(String sosUrl) throws IOException {
        super(sosUrl);
    }

    protected void setUp() throws Exception {
        JSONArray json = JsonReaderCustom.read(source);
        cctvs = new CctvJson[json.length()];
        for( int i = 0; i < cctvs.length ; i++ )
            cctvs[i] = new CctvJson(json.getJSONObject(i));
    }

    public void run() {
        try {
            setUp();
            for( CctvJson cctv : cctvs ){
                SwcbInsertSensorAdapter adpater = new SwcbInsertSensorAdapter(cctv);
                InsertSensor insertSensor = adpater.getInsertSensor();
                sosRequest.insertSensor(insertSensor);
            }

            for( CctvJson cctv : cctvs ) {
                sosRequest.insertObservation(cctv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
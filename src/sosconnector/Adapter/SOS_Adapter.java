package sosconnector.Adapter;

import sosconnector.Request.InsertRequest;
import sosconnector.SOSConnector;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/4/18.
 */
public class SOS_Adapter {

    private String url;

    public SOS_Adapter(String url){
        this.url = url;
    }

    public void insertSensor(String xml){
        sendInsertRequest(xml);
    }
    public void insertObservation(String xml){
        sendInsertRequest(xml);
    }

    protected String sendInsertRequest(String requestBody) {
        InsertRequest request = null;
        try {
            request = new InsertRequest(url);
            request.writeIn( requestBody );
            return request.getResponseBody();
        } catch (IOException e) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}

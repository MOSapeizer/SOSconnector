package sosconnector.Adapter;

import sosconnector.Filter.Analyze;
import sosconnector.Request.InsertRequest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/4/18.
 */
public class SosAdapter {

    private String url;

    public SosAdapter(String url){
        this.url = url;
    }

    public void insert(String xml){
        String response = sendInsertRequest(xml);
        System.out.println(xml);
        System.out.println(response);
        Analyze.statistic( xml, response );
    }

    protected String sendInsertRequest(String requestBody) {
        InsertRequest request = null;
        try {
            request = new InsertRequest(url);
            request.writeIn( requestBody );
            return request.getResponseBody();
        } catch (IOException e) {
            Logger.getLogger(SosAdapter.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}

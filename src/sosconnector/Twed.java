package sosconnector;

import sosconnector.Factory.TwedFactory;
import sosconnector.Request.Request;

/**
 * Created by zil on 2016/4/13.
 */
public class Twed {
    public static void main(String[] args) throws Exception {

        String url = "http://data.wra.gov.tw/Service/OpenData.aspx?id=2D09DB8B-6A1B-485E-88B5-923A462F475C&format=xml";
        Request request = new Request( url );
        request.setConnection("GET");

        //Get response
        String response = request.getResponseBody();

        //Manipulate response
        TwedFactory sir = new TwedFactory( response );

        sir.start();


    }
}

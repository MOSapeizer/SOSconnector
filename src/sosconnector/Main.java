package sosconnector;

import org.json.JSONArray;
import org.json.JSONObject;
import sosconnector.DAO.DAOFactory;
import sosconnector.Request.Request;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/4/21.
 */
public class Main {

    private final static int seconds = 1000;
    private final static int minutes = 60 * seconds;
    private final static int hours = 60 * minutes;
    private final static int days = 24 * hours;

    private static String getSourceFormGOV(String url){
        try {
            return new Request("GET", url).getResponseBody();
        } catch (IOException e) {
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, "Can't get Resource From gov URL.", e);
        }
        return null;
    }

    public static void main(String[] args) throws JAXBException {
//        Timer timer = new Timer( true );
//        timer.schedule(new Twed(), 5 * seconds, 10 * minutes);
//        System.out.println("現在時間：" + new Date());
//
//        try {
//            Thread.sleep(3 * days);
//        } catch (InterruptedException ignored) {
//        }
//
//        System.out.println("結束時間：" + new Date());
//        timer.cancel();

        String response = getSourceFormGOV("http://data.coa.gov.tw/Service/OpenData/DataFileService.aspx?UnitId=111");
//        System.out.println(response);
        JSONArray json = new JSONArray(response);

        for(  int i = 0 ; i < json.length(); i++ ){
            JSONObject j = (JSONObject) json.get(i);
            System.out.println(j);
        }
    }
}

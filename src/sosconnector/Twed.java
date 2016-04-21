package sosconnector;


import sosconnector.DAO.TwedDAO;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by zil on 2016/4/20.
 */
public class Twed {

    private final static String url = "http://data.wra.gov.tw/Service/OpenData.aspx?id=2D09DB8B-6A1B-485E-88B5-923A462F475C&format=xml";
    private final static String filePath = "src/sosconnector/GovConfigure/twed.xml";

    public static void main(String[] args){

        TwedDAO twed = new TwedDAO(url, filePath);
        try {
            System.out.println( twed.getInsertSensorXML().getFirst());
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}
package sosconnector;


import sosconnector.DAO.TwedDAO;
import sosconnector.XML.TwedXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;


/**
 * Created by zil on 2016/4/20.
 */
public class Twed {

    private static Object unmarshal(File configure) throws JAXBException {
        JAXBContext jaxb = JAXBContext.newInstance(Configure.class);
        Unmarshaller unmarshaller = jaxb.createUnmarshaller();
        return unmarshaller.unmarshal(configure);
    }

    public static void main(String[] args){
        String url = "http://data.wra.gov.tw/Service/OpenData.aspx?id=2D09DB8B-6A1B-485E-88B5-923A462F475C&format=xml";
        String filePath = "src/sosconnector/GovConfigure/twed.xml";

        TwedDAO twed = new TwedDAO(url, filePath);
        try {
            System.out.println( twed.getInsertSensorXML().getFirst());
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}
package sosconnector;


import sosconnector.DTO.DTOFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by zil on 2016/4/13.
 */
public class Twed {

    public static void main(String[] args) throws Exception {

//        String eapUrl = "http://localhost:8080/epa-aqx-sos/service";
        String url = "http://data.wra.gov.tw/Service/OpenData.aspx?id=2D09DB8B-6A1B-485E-88B5-923A462F475C&format=xml";

        DTOFactory d = new DTOFactory(new File("SOSConnector/src/sosconnector/Configure/twed.xml"), "");
        Configure c = (Configure) d.unmarshal();




    }
}

package sosconnector;

import sosconnector.Configure.Configure;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Arrays;

/**
 * Created by zil on 2016/6/16.
 */
public class TestConfigure {
    private static Configure unmarshal(File configure) throws JAXBException {
        JAXBContext jaxb = JAXBContext.newInstance(Configure.class);
        Unmarshaller unmarshaller = jaxb.createUnmarshaller();
        return (Configure) unmarshaller.unmarshal(configure);
    }

    public static void main(String[] args) throws JAXBException {
        File file = new File("src/sosconnector/Configure/sample.xml");
        Configure c = unmarshal(file);
        System.out.println(c.getInsertSensor().getPropertyPrefix().value);
    }
}

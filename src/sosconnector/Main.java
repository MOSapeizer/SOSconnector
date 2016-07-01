package sosconnector;

import sosconnector.Configure.Configure;
import sosconnector.Parser.XmlConfigureParser;

import javax.xml.bind.JAXBException;
import java.nio.file.NotDirectoryException;
import java.util.Timer;


/**
 * Created by zil on 2016/4/21.
 */
public class Main {

    public static final String configures_path = "src/sosconnector/Configure/";
    public static final int start_buff = 15 * 1000;

    public static void main(String[] args) throws JAXBException, NotDirectoryException {
        XmlConfigureParser xmlConfigureParser = new XmlConfigureParser( configures_path );
        Configure[] configures = xmlConfigureParser.configures();
        Timer timer = new Timer( true );

        for (Configure configure : configures) {
            Connector connector = new Connector(configure);
            timer.schedule(connector, start_buff, connector.getPeriod());
        }
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException ignored) {
        }
    }
}

package sosconnector.Parser;

import sosconnector.Configure.Configure;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * Created by zil on 2016/6/16.
 */
public class XmlConfigureParser {

    private final String dir;

    public XmlConfigureParser(){
        dir = "src/sosconnector/Configure/";
    }

    public XmlConfigureParser(String dir){
        this.dir = dir;
    }

    public Configure[] configures() {
        File file = new File(dir);
        FilenameFilter xmlFilter = xmlFilter();
        File[] files = file.listFiles(xmlFilter);
        return groupConfigure(files);
    }

    private FilenameFilter xmlFilter(){
        return (dir, name) -> name.contains(".xml");
    }

    private Configure[] groupConfigure(File[] configures) {
        int index = configures.length;
        Configure[] configureGroup = new Configure[index];
        for ( File configure : configures) {
            try {
                configureGroup[--index] = unmarshall(configure);
            } catch (JAXBException ignored) {}
        }
        return configureGroup;
    }

    private Configure unmarshall(File configure) throws JAXBException {
        JAXBContext jaxb = JAXBContext.newInstance(Configure.class);
        Unmarshaller unmarshaller = jaxb.createUnmarshaller();
        return (Configure) unmarshaller.unmarshal(configure);
    }
}

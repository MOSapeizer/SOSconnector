package sosconnector.Parser;

import sosconnector.Configure.Configure;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;

/**
 * Created by zil on 2016/6/16.
 */
public class XmlConfigureParser {

    private final File file;

    public XmlConfigureParser(){
        file = new File("src/sosconnector/Configure/");
    }

    public XmlConfigureParser(String dir) throws NotDirectoryException {
        if( !dir.endsWith("/") )
            throw new NotDirectoryException("Need Directory");
        this.file = new File(dir);
    }

    public Configure[] configures() {
        File[] files = getXmlFiles(file);
        return groupConfigure(files);
    }

    private static File[] getXmlFiles(File file){
        FilenameFilter fileFilter = (dir, name) -> name.contains(".xml");
        return file.listFiles(fileFilter);
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

package sosconnector.DTO;

import sosconnector.Configure;
import sosconnector.Factory.DomParser;
import sosconnector.Parser.NodeParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by zil on 2016/4/19.
 */
public class DTOFactory {
    private File configure;
    private LinkedList<HashMap> data;

    public DTOFactory(){

    }

    public DTOFactory(File configure, String source){
        this.configure = configure;
        this.data = getData( source );
    }

    //這邊用來產生DTO，目前資源有一個充滿hashmap的linkedList
    public void make(){

    }

    private LinkedList<HashMap> getData( String source ){
        try {
            DomParser dom = new DomParser(source);
            NodeParser nodeParser = new NodeParser( dom , (Configure) unmarshal());
            return nodeParser.parse();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object unmarshal() throws JAXBException {
        JAXBContext jaxb = JAXBContext.newInstance(Configure.class);
        Unmarshaller unmarshaller = jaxb.createUnmarshaller();
        return unmarshaller.unmarshal(configure);
    }
}

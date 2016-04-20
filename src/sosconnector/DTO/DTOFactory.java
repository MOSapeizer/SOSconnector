package sosconnector.DTO;

import sosconnector.Configure;
import sosconnector.Factory.DomParser;
import sosconnector.Parser.NodeParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/4/19.
 */
public class DTOFactory {
    private File configure;
    private LinkedList<LinkedHashMap> dataList;
    private Set keySet;

    public DTOFactory(){

    }

    public DTOFactory(File configure, String source){
        this.configure = configure;
        this.dataList = getData( source );
        this.keySet = getKeySet();
    }

    public LinkedList<Object> make(Class c){
        LinkedList<Object> collect = new LinkedList<>();
        collectDTO( c, collect );
        return collect;
    }

    public LinkedList<LinkedHashMap> getDataList(){
        return dataList;
    }

    private LinkedList<Object> collectDTO(Class c, LinkedList<Object> collect ){
        for( LinkedHashMap data : dataList ){
            try {
                Object transform = transform(c, data);
                collect.push( transform );
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                Logger.getLogger(DTOFactory.class.getName()).log(Level.SEVERE, "Trouble with create DTO", e);
            }
        }
        return collect;
    }

    private Set getKeySet(){
        return dataList.getFirst().keySet();
    }

    private Object transform(Class c, LinkedHashMap data) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        int size = keySet.size();
        Object[] key = keySet.toArray();
        Object[] params = new Object[size];
        for( int i = 0 ; i < size ; i++ ){
            params[i] = data.get(key[i]);
        }
        Constructor constructor = c.getConstructors()[0];
        return constructor.newInstance(params);
    }

    private LinkedList<LinkedHashMap> getData(String source ){
        try {
            DomParser dom = new DomParser(source);
            NodeParser nodeParser = new NodeParser( dom , (Configure) unmarshal());
            return nodeParser.parse();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object unmarshal() throws JAXBException {
        JAXBContext jaxb = JAXBContext.newInstance(Configure.class);
        Unmarshaller unmarshaller = jaxb.createUnmarshaller();
        return unmarshaller.unmarshal(configure);
    }
}

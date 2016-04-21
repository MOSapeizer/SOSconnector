package sosconnector.DAO;

import sosconnector.DTO.DTOFactory;
import sosconnector.Request.Request;
import sosconnector.XML.ObservationXML;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/4/1.
 */

abstract public class DAOFactory {


    private String url;
    private Class dtoClass = setDtoClass();
    private Class template = setXmlTemplate();
    private LinkedList<Object> dtoGroup;

    public DAOFactory(String url, String configure_path){
        this.url = url;
        this.dtoGroup = dtoFactory( configure_path,  getSourceFormGOV() ).make( dtoClass );
    }

    protected abstract Class setXmlTemplate();

    protected abstract Class setDtoClass();

    public LinkedList<String> getInsertSensorXML(Class c) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        LinkedList<String> sensorXMLGroup = new LinkedList<>();
        for( Object dto : dtoGroup ){
            Constructor constructor = c.getConstructors()[0];
            ObservationXML xml = (ObservationXML) constructor.newInstance(dto);
            String sensorXML = xml.getInsertSensorXml();
            sensorXMLGroup.push( sensorXML );
        }
        return sensorXMLGroup;
    }

    public String getInsertObservationXML(){
        return null;
    }

    private DTOFactory dtoFactory(String configure_path, String source){
        File file = new File( configure_path );
        return new DTOFactory( file, source);
    }

    private String getSourceFormGOV(){
        try {
            return new Request("GET", url).getResponseBody();
        } catch (IOException e) {
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, "Can't get Resource From gov URL.", e);
        }
        return null;
    }

}

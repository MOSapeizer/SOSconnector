package sosconnector.DAO;

import sosconnector.DTO.DTOFactory;
import sosconnector.Request.Request;
import sosconnector.XML.ObservationXML;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public LinkedList<String> getInsertSensorXML() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        LinkedList<String> sensorXMLGroup = new LinkedList<>();
        for( Object dto : dtoGroup ){
            String sensorXML = invoke(newInstanceOfXML(dto), "getInsertSensorXml");
            sensorXMLGroup.push( sensorXML );
        }
        return sensorXMLGroup;
    }

    public LinkedList<String> getInsertObservationXML() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        LinkedList<String> observationXMLGroup = new LinkedList<>();
        for( Object dto : dtoGroup ){
            String observationXML = invoke(newInstanceOfXML(dto), "getInsertObservationXML");
            observationXMLGroup.push( observationXML );
        }
        return observationXMLGroup;
    }

    private String invoke(Object obj, String method){
        Class c = obj.getClass();
        try {
            Method getInsertSensorXML = c.getMethod(method);
            return (String) getInsertSensorXML.invoke(obj);
        } catch ( NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, "Cant invoke object method", e);
        }
        return "";
    }

    private Object newInstanceOfXML(Object params) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = template.getConstructors()[0];
        return constructor.newInstance(params);
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

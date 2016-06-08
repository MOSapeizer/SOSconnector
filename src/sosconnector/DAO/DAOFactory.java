package sosconnector.DAO;

import sosconnector.DTO.DTOFactory;
import sosconnector.DTO.JsonDTOFactory;
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
    protected Class siteClass = null;
    private LinkedList<Object> siteDtoGroup;
    private LinkedList<Object> dtoGroup;

    public DAOFactory(String url){
        this.url = url;
        this.dtoGroup = new JsonDTOFactory( getSourceFormGOV() ).make( dtoClass );
    }

    public DAOFactory(String url, String configure_path){
        this.url = url;
        this.dtoGroup = dtoFactory( configure_path,  getSourceFormGOV() ).make( dtoClass );
    }

    public void setSiteResource(String url, String configure_path , Class dto){
        this.siteClass = dto;
        this.siteDtoGroup = dtoFactory( configure_path,  getSourceFormGOV(url) ).make( siteClass );
    }

    protected Object injectDtoGroup(Object dto, LinkedList siteDtoGroup){
        return null;
    }

    protected abstract Class setXmlTemplate();

    protected abstract Class setDtoClass();

    public LinkedList<String> getInsertSensorXML() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        LinkedList<String> sensorXMLGroup = new LinkedList<>();
        collectXML(sensorXMLGroup, "getInsertSensorXml");
        return sensorXMLGroup;
    }

    public LinkedList<String> getInsertObservationXML() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        LinkedList<String> observationXMLGroup = new LinkedList<>();
        collectXML(observationXMLGroup, "getInsertObservationXML");
        return observationXMLGroup;
    }

    private void collectXML(LinkedList xmlGroup, String method)  throws IllegalAccessException, InstantiationException, InvocationTargetException {
        for( Object dto : dtoGroup ){
            String xml = "";
            if( siteClass == null )
                xml = invoke(newInstanceOfXML(dto), method);
            else{
                Object obj = injectDtoGroup(dto, siteDtoGroup);
                xml = invoke(obj, method);
            }
            xmlGroup.push( xml );
        }
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
        Constructor[] constructors = template.getConstructors();
        Constructor constructor = constructors[0];
        return constructor.newInstance(params);
    }

    private Object newInstanceOfXML(Object[] params) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor[] constructors = template.getConstructors();
        Constructor constructor = constructors[0];
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

    private String getSourceFormGOV(String url){
        try {
            return new Request("GET", url).getResponseBody();
        } catch (IOException e) {
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, "Can't get Resource From gov URL.", e);
        }
        return null;
    }

}

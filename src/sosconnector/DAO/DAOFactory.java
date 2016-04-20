package sosconnector.DAO;

import sosconnector.DTO.DTOFactory;
import sosconnector.Request.Request;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/4/1.
 */

//必須重構到只剩下getInsertSensorXML還有getInsertObservationXML就好
//    參數的部分只需要考慮URL和設定檔的黨名。
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

    public String[] getInsertSensorXML(){

        return null;
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

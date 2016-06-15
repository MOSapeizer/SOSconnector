package sosconnector.Parser;

import sosconnector.DAO.DAOFactory;
import sosconnector.Request.Request;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/6/15.
 */
public class SoureParser {

    private final String url;
    private final String configure;

    public SoureParser(String url, String configure_path){
        this.url = url;
        this.configure = configure_path;
    }

    private String getSourceFormGOV(){
        return getSourceFormGOV(url);
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

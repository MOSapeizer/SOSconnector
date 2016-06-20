package sosconnector.Parser;

import sosconnector.Configure.Info;
import sosconnector.Request.Request;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/6/15.
 */
public class SourceParser {

    private final String url;

    public SourceParser(Info info){
        this.url = info.getUrl();
    }

    public String getSourceFormGOV(){
        return getSourceFormGOV(url);
    }

    public String getSourceFormGOV(String url){
        try {
            return new Request("GET", url).getResponseBody();
        } catch (IOException e) {
            Logger.getLogger(SourceParser.class.getName()).log(Level.SEVERE, "Can't get Resource From gov URL.", e);
        }
        return null;
    }
}

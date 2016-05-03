package sosconnector.DTO;

import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/4/29.
 */
public class JsonDTOFactory {

    private String[] keySet;
    private JSONArray source;

    public JsonDTOFactory(String source){
        this.source = new JSONArray(source);
        this.keySet = getKeySet();
    }

    public LinkedList<Object> make(Class c){
        LinkedList<Object> collect = new LinkedList<>();
        collectDTO( c, collect );
        return collect;
    }

    private void collectDTO(Class c, LinkedList<Object> collect){
        for(  int i = 0 ; i < source.length(); i++ ){
            try {
                Object transform = transform(c, (JSONObject) source.get(i));
                collect.push(transform);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                Logger.getLogger(JsonDTOFactory.class.getName()).log(Level.SEVERE, "Trouble with create DTO", e);
            }
        }
    }

    private Object transform(Class c, JSONObject json ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Object[] params = new Object[keySet.length];
        for( int i = 0 ; i < keySet.length ; i++ )
            params[i] = json.get(keySet[i]);

        Constructor constructor = c.getConstructors()[0];
        return constructor.newInstance(params);
    }

    private String[] getKeySet() {
        return new String[]{ "行政區", "縣市", "攝影機名稱", "影像連結網址", "架設或拍攝地點", "緯度", "經度" };
    }
}
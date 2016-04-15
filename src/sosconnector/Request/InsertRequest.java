package sosconnector.Request;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Created by zil on 2016/3/30.
 */
public class InsertRequest extends Request {

    public InsertRequest(String urlString) throws IOException {
        super(urlString);
        this.connection = setConnection();
    }


    public HttpURLConnection setConnection() throws IOException {
        HttpURLConnection connection = super.setConnection("POST");
        connection.setRequestProperty("Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
        return connection;
    }


}

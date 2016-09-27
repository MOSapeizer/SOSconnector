package Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/3/30.
 */
public class Request {

    private URL url;
    HttpURLConnection connection = null;
    private final static String USER_AGENT = "Mozilla/5.0";

    public Request(String url){
        this.url = setURL( url );
    }

    public String get() throws IOException {
        setConnection("GET");
        return getResponse();
    }

    public String post(String body) throws IOException {
        setConnection("POST");
        write(body);
        return getResponse();
    }

    private void write(String body) throws IOException {
        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        wr.write( body );
        wr.flush();
        wr.close();
    }

    private String getResponse() throws IOException{
        InputStreamReader connecionReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
        BufferedReader in = new BufferedReader( connecionReader );
        return readFrom(in);
    }

    private String readFrom(BufferedReader in) throws IOException {
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    protected void setConnection(String method) throws IOException {
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        if( method.equals("POST") ) {
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
        }
        connection.setRequestMethod(method);
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Charset", "UTF-8");

        setConnectionOther();
    }

    public void setConnectionOther(){

    }

    private URL setURL(String urlString) {
        try {
            return  new URL(urlString);
        } catch (MalformedURLException e) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}

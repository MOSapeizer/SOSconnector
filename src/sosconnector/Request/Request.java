package sosconnector.Request;

import sosconnector.SOSConnector;

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

    Request(String urlString){
        this.url = setURL( urlString );
    }

    public Request(String method, String urlString) throws IOException {
        this.url = setURL( urlString );
        setConnection(method);
    }

    public void writeIn(String input) throws IOException {
        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        wr.write( input );
        wr.flush();
        wr.close();
    }

    public String getResponseBody() throws IOException{
        BufferedReader in = getBufferedReader();
        return readFrom(in);
    }


    HttpURLConnection setConnection(String method) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true); // Triggers POST.
        connection.setRequestMethod(method);
        connection.setRequestProperty("User-Agent", USER_AGENT);
        this.connection = connection;
        return connection;
    }

    private URL setURL(String urlString) {
        try {
            return  new URL(urlString);
        } catch (MalformedURLException e) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private BufferedReader getBufferedReader() throws IOException {
        return new BufferedReader( getConnectionReader() );
    }

    private InputStreamReader getConnectionReader() throws IOException {
        return new InputStreamReader(connection.getInputStream(), "UTF-8");
    }

    private String readFrom(BufferedReader in) throws IOException {
        String inputLine;
        StringBuilder out = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            out.append(inputLine);
        }
        in.close();
        return out.toString();
    }

}

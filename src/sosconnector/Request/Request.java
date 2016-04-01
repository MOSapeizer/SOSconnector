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

    public URL url;
    public HttpURLConnection connection = null;
    private final static String USER_AGENT = "Mozilla/5.0";

    public Request(String urlString){
        this.url = setURL( urlString );
    }

    protected URL setURL(String urlString) {
        try {
            return  new URL(urlString);
        } catch (MalformedURLException e) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public HttpURLConnection setConnection(String method) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true); // Triggers POST.
        connection.setRequestMethod(method);
        connection.setRequestProperty("User-Agent", USER_AGENT);
        this.connection = connection;
        return connection;
    }

    public HttpURLConnection getConnection(){
        return this.getConnection();
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

    protected BufferedReader getBufferedReader() throws IOException {
        return new BufferedReader( getConnectionReader() );
    }

    private InputStreamReader getConnectionReader() throws IOException {
        return new InputStreamReader(connection.getInputStream(), "UTF-8");
    }

    protected String readFrom(BufferedReader in) throws IOException {
        String inputLine;
        StringBuffer out = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            out.append(inputLine);
        }
        in.close();
        return out.toString();
    }







}

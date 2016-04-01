package sosconnector;

import com.sun.java.browser.plugin2.DOM;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zil on 2016/4/1.
 */
public class DomParser {

    private String source;
    public DomParser(String source){
        this.source = source;
    }

    public NodeList getDataList(){
        org.w3c.dom.Document dom = parseSource(source);
        if (dom == null) throw new AssertionError();
        return dom.getElementsByTagName("Data");
    }

    private org.w3c.dom.Document parseSource(String source) {
        try {
            DocumentBuilder builder = getDOMBuilder();
            InputSource is = new InputSource( new StringReader(source) );
            return builder.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private DocumentBuilder getDOMBuilder() throws ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
}

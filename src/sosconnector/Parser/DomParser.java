package sosconnector.Parser;

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

    public NodeList getDataList(String dataName){
        org.w3c.dom.Document dom = parseSource(source);
        if (dom == null) throw new AssertionError();
        return dom.getElementsByTagName(dataName);
    }

    private org.w3c.dom.Document parseSource(String source) {
        try {
            source = replaceIllegalChars(source);
            DocumentBuilder builder = getDOMBuilder();
            InputSource is = new InputSource( new StringReader(source) );

            return builder.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            Logger.getLogger(DomParser.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private String replaceIllegalChars(String source){
      return source.replace("\uFEFF", "");
    }

    private DocumentBuilder getDOMBuilder() throws ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

}

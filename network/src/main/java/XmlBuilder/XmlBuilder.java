package XmlBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by Zil on 2016/9/22.
 */
public class XmlBuilder {

    protected Document doc;
    protected Element root;
    protected Element now;

    public XmlBuilder(String template) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(template));
        doc = builder.parse(is);
        root = doc.getDocumentElement();
        now = root;
    }

    public XmlBuilder append(String tag){
        Element element = doc.createElement(tag);
        now = (Element) now.appendChild(element);
        return this;
    }

    public XmlBuilder attribute(String name, String value){
        now.setAttribute(name, value);
        return this;
    }

    public XmlBuilder text(String text){
        now.setTextContent(text);
        return this;
    }

    public XmlBuilder up(){
        now = (Element) now.getParentNode();
        return this;
    }

    public NodeList getList(String element){
        return doc.getElementsByTagName(element);
    }

    public String toXML(){
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }
}

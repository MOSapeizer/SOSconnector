package sosconnector.DAO;

import sosconnector.Configure.Delegate;
import sosconnector.XML.XmlTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

/**
 * Created by zil on 2016/6/20.
 */
public class XmlDAO {

    private LinkedList<String> insertSensorXmlList = new LinkedList<>();
    private LinkedList<String> insertObservationXmlList = new LinkedList<>();

    public XmlDAO(Delegate delegate){
        XmlTemplate[] xmlTemplateGroup = delegate.getXmlTemplateGroup();
        collect(xmlTemplateGroup);
    }

    public LinkedList<String> getInsertSensorXML(){
        return insertSensorXmlList;
    }

    public LinkedList<String> getInsertObservationXML(){
        return insertObservationXmlList;
    }

    private void collect(XmlTemplate[] xmlTemplateGroup){
        for (XmlTemplate xmlTemplate : xmlTemplateGroup) {
            insertSensorXmlList.add(xmlTemplate.getInsertSensorXml());
            insertObservationXmlList.add(xmlTemplate.getInsertObservationXml());
        }
    }
}

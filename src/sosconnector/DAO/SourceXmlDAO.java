package sosconnector.DAO;

import sosconnector.XML.XmlTemplate;

import java.util.LinkedList;

/**
 * Created by zil on 2016/6/20.
 */
public class SourceXmlDAO {

    private LinkedList<String> insertSensorXmlList = new LinkedList<>();
    private LinkedList<String> insertObservationXmlList = new LinkedList<>();

    public SourceXmlDAO(ConfigureDAO configureDAO){
        XmlTemplate[] xmlTemplateGroup = configureDAO.getXmlTemplateGroup();
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

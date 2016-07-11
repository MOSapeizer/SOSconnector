package sosconnector.DAO;

import sosconnector.XML.XmlTemplate;

import java.util.LinkedList;

/**
 * Created by zil on 2016/6/20.
 */
public class SourceXmlDAO {

    private final ConfigureDAO configureDAO;
    private LinkedList<String> insertSensorXmlList;
    private LinkedList<String> insertObservationXmlList;

    public SourceXmlDAO(ConfigureDAO configureDAO){
        this.configureDAO = configureDAO;
    }

    public void updateInsertXML(){
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
        insertSensorXmlList = new LinkedList<>();
        insertObservationXmlList = new LinkedList<>();
        for (XmlTemplate xmlTemplate : xmlTemplateGroup) {
            insertSensorXmlList.add(xmlTemplate.getInsertSensorXml());
            insertObservationXmlList.add(xmlTemplate.getInsertObservationXml());
        }
    }
}

package sosconnector.DAO;

import sosconnector.DTO.SwcbDTO;
import sosconnector.XML.SwcbXML;

import java.util.LinkedList;

/**
 * Created by zil on 2016/4/29.
 */
public class SwcbDAO extends DAOFactory {

    public SwcbDAO(String url) {
        super(url);
    }

    @Override
    protected Class setXmlTemplate() {
        return SwcbXML.class;
    }

    @Override
    protected Class setDtoClass() {
        return SwcbDTO.class;
    }
}

package sosconnector.DAO;

import sosconnector.DTO.CwbSeaLevelDTO;
import sosconnector.XML.CwbSeaLevelXML;

/**
 * Created by zil on 2016/6/6.
 */
public class CwbSeaLevelDAO extends DAOFactory {

    public CwbSeaLevelDAO(String url, String configure_path) {
        super(url, configure_path);
    }

    @Override
    protected Class setXmlTemplate() {
        return CwbSeaLevelXML.class;
    }

    @Override
    protected Class setDtoClass() {
        return CwbSeaLevelDTO.class;
    }
}
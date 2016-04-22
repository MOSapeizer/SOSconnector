package sosconnector.DAO;

import sosconnector.DTO.CwbSeaDTO;
import sosconnector.XML.CwbSeaXML;

/**
 * Created by zil on 2016/4/22.
 */
public class CwbSeaDAO extends DAOFactory {

    public CwbSeaDAO(String url, String configure_path) {
        super(url, configure_path);
    }

    @Override
    protected Class setXmlTemplate() {
        return CwbSeaXML.class;
    }

    @Override
    protected Class setDtoClass() {
        return CwbSeaDTO.class;
    }
}

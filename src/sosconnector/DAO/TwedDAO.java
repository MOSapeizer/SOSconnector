package sosconnector.DAO;

import sosconnector.DTO.TwedDTO;
import sosconnector.XML.TwedXML;

/**
 * Created by zil on 2016/4/19.
 */
public class TwedDAO extends DAOFactory {

    public TwedDAO(String url, String configure_path) {
        super(url, configure_path);
    }

    @Override
    protected Class setXmlTemplate() {
        return TwedDTO.class;
    }

    @Override
    protected Class setDtoClass() {
        return TwedXML.class;
    }
}

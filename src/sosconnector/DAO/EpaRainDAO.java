package sosconnector.DAO;

import sosconnector.DTO.EpaRainDTO;
import sosconnector.XML.EpaRainXML;

/**
 * Created by zil on 2016/6/1.
 */
public class EpaRainDAO extends DAOFactory {

    public EpaRainDAO(String url, String configure_path) {
        super(url, configure_path);
    }

    @Override
    protected Class setXmlTemplate() {
        return EpaRainXML.class;
    }

    @Override
    protected Class setDtoClass() {
        return EpaRainDTO.class;
    }
}

package sosconnector.DAO;


import sosconnector.DTO.EpaAqxDTO;
import sosconnector.DTO.EpaAqxSiteDTO;
import sosconnector.XML.EpaAqxXML;

import java.util.LinkedList;

/**
 * Created by zil on 2016/6/8.
 */
public class EpaAqxDAO extends DAOFactory {

    public EpaAqxDAO(String url, String configure_path) {
        super(url, configure_path);
    }

    @Override
    protected Class setXmlTemplate() {
        return EpaAqxXML.class;
    }

    @Override
    protected Class setDtoClass() {
        return EpaAqxDTO.class;
    }

    @Override
    protected Object injectDtoGroup(Object dto, LinkedList siteDtoGroup) {
        EpaAqxDTO epa = (EpaAqxDTO) dto;
        for( Object obj: siteDtoGroup){
            EpaAqxSiteDTO epaSite = (EpaAqxSiteDTO) obj;
            if( epa.getSiteName() == epaSite.getSiteName())
                return new EpaAqxXML(epa, epaSite);
        }
        return null;
    }
}
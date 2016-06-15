package sosconnector.XML;

import sosconnector.DTO.ObservationDTO;
import sosconnector.DTO.SiteDTO;

/**
 * Created by zil on 2016/6/15.
 */
public class XmlTemplate {

    private SiteDTO site;
    private ObservationDTO observation;

    public XmlTemplate(SiteDTO site, ObservationDTO observation){
        this.site = site;
        this.observation = observation;
    }
}

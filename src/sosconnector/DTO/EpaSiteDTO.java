package sosconnector.DTO;

import org.w3c.dom.Element;

/**
 * Created by zil on 2016/3/30.
 */
public class EpaSiteDTO {
    private Element node;
    private String SiteName;
    private String SiteEngName;
    private String AreaName;
    private String County;
    private String Township;
    private String SiteAddress;
    private String TWD97Lon;
    private String TWD97Lat;
    private String SiteType;

    public EpaSiteDTO(Element node) {
        this.node = node;
        this.SiteName = getTagContent("SiteName");
        this.SiteEngName = getTagContent("SiteEngName");
        this.AreaName =  getTagContent("AreaName");
        this.County = getTagContent("County");
        this.Township = getTagContent("Township");
        this.TWD97Lon = getTagContent("TWD97Lon");
        this.TWD97Lat = getTagContent("TWD97Lat");
        this.SiteType = getTagContent("SiteType");
    }
    public String getTagContent(String tag) {
        return node.getElementsByTagName(tag).item(0).getTextContent();
    }
    public String getSiteName() {
        return this.SiteName;
    }
    public String getSiteEngName() {
        return this.SiteEngName;
    }
    public String getAreaName() {
        return this.AreaName;
    }
    public String getCounty() {
        return this.County;
    }
    public String getTownship() {
        return this.Township;
    }
    public String getSiteAddress() {
        return this.SiteAddress;
    }
    public String getTWD97Lon() {
        return this.TWD97Lon;
    }
    public String getTWD97Lat() {
        return this.TWD97Lat;
    }
    public String getSiteType() {
        return this.SiteType;
    }

}

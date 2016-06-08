package sosconnector.DTO;

import org.w3c.dom.Element;

/**
 * Created by zil on 2016/3/30.
 */
public class EpaAqxSiteDTO {

    private String SiteName;
    private String SiteEngName;
    private String AreaName;
    private String County;
    private String Township;
    private String SiteAddress;
    private String TWD97Lon;
    private String TWD97Lat;
    private String SiteType;

    public EpaAqxSiteDTO(String siteName, String siteEngName, String areaName, String county, String township, String siteAddress, String twd97Lon, String twd97Lat, String siteType) {
        SiteName = siteName;
        SiteEngName = siteEngName;
        AreaName = areaName;
        County = county;
        Township = township;
        SiteAddress = siteAddress;
        TWD97Lon = twd97Lon;
        TWD97Lat = twd97Lat;
        SiteType = siteType;
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

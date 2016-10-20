package Swcb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Zil on 2016/10/18.
 */

@XmlRootElement(name = "Row")
public class Row {


    private String name;
    private String tenMinute;
    private String oneHour;
    private String threeHour;
    private String sixHour;
    private String oneDay;
    private String _24Hour;
    private String rtime;
    private String _12Hour;

    private String stationID;
    private String stationName;
    private String stationEnglishName;
    private String longitude;
    private String latitude;
    private String district;
    private String debrisNO;
    private String disaster;
    private String river;
    private String drainage;
    private String catchment;
    private String subCatchment;
    private String engDistrict;
    private String engDebrisNO;
    private String engDisaster;
    private String engRiver;
    private String engDrainage;
    private String engCatchment;
    private String engSubCatchment;
    private String imageURL;
    private String stationURL;

    public Row() {
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "tenMinute")
    public String getTenMinute() {
        return tenMinute;
    }

    public void setTenMinute(String tenMinute) {
        this.tenMinute = tenMinute;
    }

    @XmlElement(name = "oneHour")
    public String getOneHour() {
        return oneHour;
    }

    public void setOneHour(String oneHour) {
        this.oneHour = oneHour;
    }

    @XmlElement(name = "threeHour")
    public String getThreeHour() {
        return threeHour;
    }

    public void setThreeHour(String threeHour) {
        this.threeHour = threeHour;
    }

    @XmlElement(name = "sixHour")
    public String getSixHour() {
        return sixHour;
    }

    public void setSixHour(String sixHour) {
        this.sixHour = sixHour;
    }

    @XmlElement(name = "oneDay")
    public String getOneDay() {
        return oneDay;
    }

    public void setOneDay(String oneDay) {
        this.oneDay = oneDay;
    }

    @XmlElement(name = "_24Hour")
    public String get_24Hour() {
        return _24Hour;
    }

    public void set_24Hour(String _24Hour) {
        this._24Hour = _24Hour;
    }

    @XmlElement(name = "rtime")
    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    @XmlElement(name = "_12Hour")
    public String get_12Hour() {
        return _12Hour;
    }

    public void set_12Hour(String _12Hour) {
        this._12Hour = _12Hour;
    }

    @XmlElement(name = "StationID")
    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    @XmlElement(name = "StationName")
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @XmlElement(name = "StationEnglishName")
    public String getStationEnglishName() {
        return stationEnglishName;
    }

    public void setStationEnglishName(String stationEnglishName) {
        this.stationEnglishName = stationEnglishName;
    }

    @XmlElement(name = "Longitude")
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @XmlElement(name = "Latitude")
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @XmlElement(name = "District")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @XmlElement(name = "DebrisNO")
    public String getDebrisNO() {
        return debrisNO;
    }

    public void setDebrisNO(String debrisNO) {
        this.debrisNO = debrisNO;
    }

    @XmlElement(name = "Disaster")
    public String getDisaster() {
        return disaster;
    }

    public void setDisaster(String disaster) {
        this.disaster = disaster;
    }

    @XmlElement(name = "River")
    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }

    @XmlElement(name = "Drainage")
    public String getDrainage() {
        return drainage;
    }

    public void setDrainage(String drainage) {
        this.drainage = drainage;
    }

    @XmlElement(name = "Catchment")
    public String getCatchment() {
        return catchment;
    }

    public void setCatchment(String catchment) {
        this.catchment = catchment;
    }

    @XmlElement(name = "SubCatchment")
    public String getSubCatchment() {
        return subCatchment;
    }

    public void setSubCatchment(String subCatchment) {
        this.subCatchment = subCatchment;
    }

    @XmlElement(name = "EngDistrict")
    public String getEngDistrict() {
        return engDistrict;
    }

    public void setEngDistrict(String engDistrict) {
        this.engDistrict = engDistrict;
    }

    @XmlElement(name = "EngDebrisNO")
    public String getEngDebrisNO() {
        return engDebrisNO;
    }

    public void setEngDebrisNO(String engDebrisNO) {
        this.engDebrisNO = engDebrisNO;
    }

    @XmlElement(name = "EngDisaster")
    public String getEngDisaster() {
        return engDisaster;
    }

    public void setEngDisaster(String engDisaster) {
        this.engDisaster = engDisaster;
    }

    @XmlElement(name = "EngRiver")
    public String getEngRiver() {
        return engRiver;
    }

    public void setEngRiver(String engRiver) {
        this.engRiver = engRiver;
    }

    @XmlElement(name = "EngDrainage")
    public String getEngDrainage() {
        return engDrainage;
    }

    public void setEngDrainage(String engDrainage) {
        this.engDrainage = engDrainage;
    }

    @XmlElement(name = "EngCatchment")
    public String getEngCatchment() {
        return engCatchment;
    }

    public void setEngCatchment(String engCatchment) {
        this.engCatchment = engCatchment;
    }

    @XmlElement(name = "EngSubCatchment")
    public String getEngSubCatchment() {
        return engSubCatchment;
    }

    public void setEngSubCatchment(String engSubCatchment) {
        this.engSubCatchment = engSubCatchment;
    }

    @XmlElement(name = "ImageURL")
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @XmlElement(name = "StationURL")
    public String getStationURL() {
        return stationURL;
    }

    public void setStationURL(String stationURL) {
        this.stationURL = stationURL;
    }
}


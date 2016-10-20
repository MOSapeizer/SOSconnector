package Swcb;

/**
 * Created by Zil on 2016/9/29.
 */

import org.json.JSONObject;

public class CctvJson {
    private String cameraName;
    private String city;
    private String county;
    private String address;
    private String url;
    private double lat;
    private double lon;

    public CctvJson(){

    }

    public CctvJson(JSONObject object) {
        this.cameraName = object.getString("攝影機名稱");
        this.city = object.getString("縣市");
        this.county = object.getString("行政區");
        this.address = object.getString("架設或拍攝地點");
        this.url = object.getString("影像連結網址").replaceAll("&", "&amp;");
        this.lat = Double.parseDouble( object.getString("緯度") );
        this.lon = Double.parseDouble( object.getString("經度") );
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}

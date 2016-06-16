package sosconnector.Configure;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by zil on 2016/6/15.
 */

@XmlType
public class InsertSensor {

    private String offering;
    private String procedure;
    private String prefix;
    private PropertyPrefix propertyPrefix;
    private Property[] property;
    private String longitude;
    private String latitude;

    public InsertSensor(){

    }

    public InsertSensor(String offering, String procedure, String prefix, PropertyPrefix propertyPrefix, Property[] property, String longitude, String latitude) {
        this.offering = offering;
        this.procedure = procedure;
        this.prefix = prefix;
        this.propertyPrefix = propertyPrefix;
        this.property = property;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @XmlElement
    public String getOffering() {
        return offering;
    }

    public void setOffering(String offering) {
        this.offering = offering;
    }

    @XmlElement
    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    @XmlElement
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @XmlElement
    public PropertyPrefix getPropertyPrefix() {
        return propertyPrefix;
    }

    public void setPropertyPrefix(PropertyPrefix propertyPrefix) {
        this.propertyPrefix = propertyPrefix;
    }

    @XmlElement
    public Property[] getProperty() {
        return property;
    }

    public void setProperty(Property[] property) {
        this.property = property;
    }

    @XmlElement
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @XmlElement
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}

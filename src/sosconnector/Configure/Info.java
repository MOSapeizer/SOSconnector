package sosconnector.Configure;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by zil on 2016/6/15.
 */
@XmlType
public class Info {

    private String url;
    private String sos;
    private String xmlDataRoot;
    private Period period;

    @XmlElement
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlElement
    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @XmlElement
    public String getSos() {
        return sos;
    }

    public void setSos(String sos) {
        this.sos = sos;
    }

    @XmlElement
    public String getXmlDataRoot() {
        return xmlDataRoot;
    }

    public void setXmlDataRoot(String xmlDataRoot) {
        this.xmlDataRoot = xmlDataRoot;
    }
}
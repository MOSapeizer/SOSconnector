package sosconnector.GovConfigure;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by zil on 2016/4/26.
 */
@XmlType
public class Child {
    @XmlAttribute
    public String type;

    @XmlValue
    public String value;
}

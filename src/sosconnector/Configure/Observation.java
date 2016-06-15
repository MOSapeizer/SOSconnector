package sosconnector.Configure;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by zil on 2016/6/15.
 */

@XmlType
public class Observation {
    @XmlAttribute
    public String name;

    @XmlAttribute
    public String unit;

    @XmlAttribute
    public String type;

    @XmlValue
    public String value;
}

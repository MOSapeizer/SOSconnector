package sosconnector;

import javax.xml.bind.annotation.*;

/**
 * Created by zil on 2016/4/19.
 */

@XmlRootElement(name ="Configure")
public class Configure {
    private String className;
    private String root;
    private String[] child;
    private String type;

    public Configure(){

    }

    public Configure(String className, String root, String[] child, String type) {
        this.className = className;
        this.root = root;
        this.child = child;
        this.type = type;
    }

    @XmlElement
    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    @XmlAttribute
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @XmlElement
    public String[] getChild() {
        return child;
    }

    public void setChild(String[] child) {
        this.child = child;
    }

    @XmlElement
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

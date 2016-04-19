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

    public Configure(){

    }

    public Configure(String className, String root, String[] child) {
        this.className = className;
        this.root = root;
        this.child = child;
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
}

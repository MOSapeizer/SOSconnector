package sosconnector.Configure;

import javax.xml.bind.annotation.*;

/**
 * Created by zil on 2016/4/19.
 */

@XmlRootElement(name ="Configure")
public class Configure {
    private String className;
    private String root;
    private Child[] child;
    private Info info;
    private InsertSensor insertSensor;
    private InsertObservation insertObservation;

    public Configure(){

    }

    public Configure(String className, String root, Child[] child) {
        this.className = className;
        this.root = root;
        this.child = child;
    }

    public Configure(Info info, InsertSensor insertSensor, InsertObservation insertObservation) {
        this.info = info;
        this.insertSensor = insertSensor;
        this.insertObservation = insertObservation;
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
    public Child[] getChild() {
        return child;
    }

    public void setChild(Child[] child) {
        this.child = child;
    }

    @XmlElement
    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @XmlElement
    public InsertSensor getInsertSensor() {
        return insertSensor;
    }

    public void setInsertSensor(InsertSensor insertSensor) {
        this.insertSensor = insertSensor;
    }

    @XmlElement
    public InsertObservation getInsertObservation() {
        return insertObservation;
    }

    public void setInsertObservation(InsertObservation insertObservation) {
        this.insertObservation = insertObservation;
    }
}

package Twed;

import GML.Point;
import helper.TwdLocationHelper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Zil on 2016/9/23.
 */
@XmlRootElement
public class LocationByTWD97 {

    private Point point;

    public LocationByTWD97() {
    }

    @XmlElement
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double[] getLatLon(){
        String[] split = getPoint().getPos().split(" ");
        double x = Double.parseDouble(split[0]);
        double y = Double.parseDouble(split[1]);

        return TwdLocationHelper.tw97toWGS84(x, y);
    }
}

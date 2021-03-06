package Twed;

import GML.Point;
import helper.TwdLocationHelper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Zil on 2016/9/23.
 */

@XmlRootElement( name = "LocationByTWD67" )
public class LocationByTWD67 {

    private Point point;

    public LocationByTWD67() {
    }

    @XmlElement( name = "Point" )
    public Point getPoint(){
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double[] getLatLon(){
        String[] split = getPoint().getPos().split(" ");
        if( split.length < 2 )
            return new double[2];
        double x = Double.parseDouble(split[0]);
        double y = Double.parseDouble(split[1]);

        return TwdLocationHelper.tw67toWGS84(x, y);
    }
}

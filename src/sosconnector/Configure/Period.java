package sosconnector.Configure;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by zil on 2016/6/15.
 */

@XmlType
public class Period {
    private Integer sec;
    private Integer min;
    private Integer hour;
    private Integer day;
    private Integer week;
    private Integer month;

    public Period(){

    }

    public Period(Integer sec, Integer min, Integer hour, Integer day, Integer week, Integer month) {
        this.sec = sec;
        this.min = min;
        this.hour = hour;
        this.day = day;
        this.week = week;
        this.month = month;
    }

    @XmlElement
    public Integer getSec() {
        return sec;
    }

    public void setSec(Integer sec) {
        this.sec = sec;
    }

    @XmlElement
    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    @XmlElement
    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    @XmlElement
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @XmlElement
    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    @XmlElement
    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}

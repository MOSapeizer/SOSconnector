package sosconnector.Filter;

import sosconnector.Configure.Period;

/**
 * Created by zil on 2016/6/20.
 */
public class TimerFilter {
    private final int seconds = 1000;
    private final int minutes = 60 * seconds;
    private final int hours = 60 * minutes;
    private final int days = 24 * hours;
    private final int month = 31 * days;
    private final Period period;

    public TimerFilter(Period period){
        this.period = period;
    }

    public int getPeriod(){
        if( period.getSec() != null )
            return period.getSec() * seconds;
        else if( period.getMin() != null )
            return period.getMin() * minutes;
        else if( period.getHour() != null )
            return period.getHour() * hours;
        else if( period.getDay() != null )
            return period.getDay() * days;
        else if( period.getMonth() != null )
            return period.getMonth() * month;
        return days;
    }


}

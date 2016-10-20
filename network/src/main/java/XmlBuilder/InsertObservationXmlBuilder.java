package XmlBuilder;

import Swcb.CctvJson;
import Swcb.Row;
import Template.InsertObservationTemplate;
import Twed.WaterLevelObservation;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Zil on 2016/9/26.
 */
public class InsertObservationXmlBuilder extends XmlBuilder {
    private WaterLevelObservation observation;

    public InsertObservationXmlBuilder(WaterLevelObservation observation) throws ParserConfigurationException, IOException, SAXException {
        super(InsertObservationTemplate.sample_output(
                observation.getOffering(),
                observation.getType(),
                observation.getTimePosition(),
                observation.getProcedure(),
                observation.getObservedProperty(),
                observation.getFoiID(),
                observation.getFoiName(),
                observation.getPosition(),
                observation.getResultType(),
                observation.getUom(),
                observation.getResult()
        ));
    }

    public InsertObservationXmlBuilder(CctvJson observation) throws ParserConfigurationException, IOException, SAXException {
        super(InsertObservationTemplate.sample_cctv_output(
                observation.getCameraName(),
                "urn:ogc:object:feature:sensor:swcb:" + observation.getCameraName(),
                observation.getCameraName(),
                observation.getCameraName(),
                observation.getLat() + " " + observation.getLon(),
                observation.getUrl()
        ));
    }

    public InsertObservationXmlBuilder(Row observation) throws ParserConfigurationException, IOException, SAXException, ParseException {
        super(InsertObservationTemplate.sample_rainfall_output(
                observation.getStationName(),
                rtimeToTimeZone(observation.getRtime()),
                "urn:ogc:object:feature:sensor:swcb:" + observation.getStationName(),
                observation.getStationName(),
                observation.getStationName(),
                observation.getLatitude() + " " + observation.getLongitude(),
                observation.getTenMinute(),
                observation.getOneHour(),
                observation.getThreeHour(),
                observation.getSixHour(),
                observation.getOneDay(),
                observation.get_24Hour(),
                observation.get_12Hour()
        ));
    }

    private static String rtimeToTimeZone(String time) throws ParseException {
        String rtime = time.replace('/', '-');
        if (rtime.contains("上午")) {
            rtime = rtime.replace(" 上午 ", "T") + "+08:00";
            if (rtime.split("T")[1].startsWith("12")) {
                String rtime1 = rtime.split("T")[0] + "T";
                String rtime2 = rtime.split("T")[1].split(":")[1] + ":" + rtime.split("T")[1].split(":")[2] + ":" + rtime.split("T")[1].split(":")[3];
                String replace = rtime.split("T")[1].split(":")[0].replace("12", "00");
                rtime = rtime1 + replace + ":" + rtime2;
            }
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+08:00");
            Date date = sdf.parse(rtime);
            sdf.setTimeZone(utc);
            System.out.println(sdf.format(date).toString());
        } else {
            rtime = rtime.replace(" 下午 ", "T") + "+08:00";
            if (!rtime.split("T")[1].startsWith("12")) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+08:00");
                Date date = sdf.parse(rtime);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.HOUR, +12);
                date = cal.getTime();
                TimeZone utc = TimeZone.getTimeZone("UTC");
                sdf.setTimeZone(utc);
                System.out.println(sdf.format(date).toString());

            } else {
                TimeZone utc = TimeZone.getTimeZone("UTC");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+08:00");
                Date date = sdf.parse(rtime);
                sdf.setTimeZone(utc);
                System.out.println(sdf.format(date).toString());
            }

        }
        return rtime;
    }




}

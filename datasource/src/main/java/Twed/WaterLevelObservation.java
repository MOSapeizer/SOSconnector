package Twed;

/**
 * Created by Zil on 2016/9/26.
 */
public class WaterLevelObservation {

    private String offering;
    private String type;
    private String timePosition;
    private String procedure;
    private String observedProperty;
    private String foiID;
    private String foiName;
    private String position;
    private String resultType;
    private String uom;
    private String result;

    public WaterLevelObservation(RiverStageObservatoryProfile profile, RealtimeWaterLevel observation){
        this.offering = profile.getBasinIdentifier().trim();
        this.type = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        this.timePosition = observation.getRecordTime() + "Z";
        this.procedure = "urn:ogc:object:feature:Sensor:twed:" + offering;
        this.observedProperty = "urn:ogc:object:feature:Sensor:twed:waterLevel";
        this.foiID = offering;
        this.foiName = profile.getObservatoryName();
        double[] latLon = profile.getLocationByTWD67().getLatLon();
        this.position = latLon[0] + "  " + latLon[1];
        this.resultType = "gml:MeasureType";
        this.uom = "m";
        this.result = observation.getWaterLevel();
    }

    public String getOffering() {
        return offering;
    }

    public String getType() {
        return type;
    }

    public String getTimePosition() {
        return timePosition;
    }

    public String getProcedure() {
        return procedure;
    }

    public String getObservedProperty() {
        return observedProperty;
    }

    public String getFoiID() {
        return foiID;
    }

    public String getFoiName() {
        return foiName;
    }

    public String getPosition() {
        return position;
    }

    public String getResultType() {
        return resultType;
    }

    public String getUom() {
        return uom;
    }

    public String getResult() {
        return result;
    }
}

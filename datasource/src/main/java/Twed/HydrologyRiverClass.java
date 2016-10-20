package Twed;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Zil on 2016/9/23.
 */

@XmlRootElement( name ="HydrologyRiverClass" )
public class HydrologyRiverClass {

    private RiverStageObservatoryProfile[] riverStageObservatoryProfile;
    private RealtimeWaterLevel[] realtimeWaterLevel;

    public HydrologyRiverClass() {
    }

    @XmlElement( name = "RiverStageObservatoryProfile" )
    public RiverStageObservatoryProfile[] getRiverStageObservatoryProfile() {
        return riverStageObservatoryProfile;
    }

    public void setRiverStageObservatoryProfile(RiverStageObservatoryProfile[] riverStageObservatoryProfile) {
        this.riverStageObservatoryProfile = riverStageObservatoryProfile;
    }

    @XmlElement( name = "RealtimeWaterLevel" )
    public RealtimeWaterLevel[] getRealtimeWaterLevel() {
        return realtimeWaterLevel;
    }

    public void setRealtimeWaterLevel(RealtimeWaterLevel[] realtimeWaterLevel) {
        this.realtimeWaterLevel = realtimeWaterLevel;
    }
}

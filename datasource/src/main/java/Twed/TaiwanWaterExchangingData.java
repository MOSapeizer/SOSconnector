package Twed;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Zil on 2016/9/23.
 */

@XmlRootElement( name = "TaiwanWaterExchangingData" )
public class TaiwanWaterExchangingData {

    private HydrologyRiverClass hydrologyRiverClass;

    public TaiwanWaterExchangingData() {
    }

    @XmlElement( name = "HydrologyRiverClass" )
    public HydrologyRiverClass getHydrologyRiverClass() {
        return hydrologyRiverClass;
    }

    public void setHydrologyRiverClass(HydrologyRiverClass hydrologyRiverClass) {
        this.hydrologyRiverClass = hydrologyRiverClass;
    }
}

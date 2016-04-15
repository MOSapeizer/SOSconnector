package sosconnector.TWED;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import sosconnector.DTO.TwedDTO;
import sosconnector.Factory.ResponseFactory;
import sosconnector.ObservationXML.TwedXML;

/**
 * Created by zil on 2016/4/12.
 */


// 預設流程
//    因為沒有額外的Site需要去request
//    所以要先從waterlevel的stationID去insertSensor
//    insertSensor的時機？
//      應該先利用
public class TwedFactory extends ResponseFactory<TwedDTO> {

    public TwedFactory(String source) {
        super(source);
    }

    @Override
    protected String setSOSUrl() {
        return "http://localhost:8080/twed_waterLevel/service";
    }

    @Override
    public void initialize() {
        setFilterNodeName("twed:RealtimeWaterLevel_OPENDATA");
    }

    @Override
    public TwedDTO operateFilteredNode(Node node) {
        TwedDTO twed = new TwedDTO( (Element) node );
        return twed;
    }

    @Override
    public Boolean whichFilteredNodeIsRedundant(TwedDTO obj) {
        return obj.getWaterLevel().equals("");
    }

    @Override
    public void finalManipulate(TwedDTO obj) {
        String resposne = sendInsertRequest( new TwedXML(obj).getInsertObservationXML() );
        writeToDocumnet( resposne );
    }

}

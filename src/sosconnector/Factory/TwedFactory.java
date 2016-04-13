package sosconnector.Factory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import sosconnector.DTO.TwedDTO;

/**
 * Created by zil on 2016/4/12.
 */
public class TwedFactory extends ResponseFactory<TwedDTO> {

    public TwedFactory(String source) {
        super(source);
    }

    @Override
    public void initialize() {
        setDataName("twed:RealtimeWaterLevel_OPENDATA");
    }

    @Override
    public TwedDTO operateNode(Node node) {
        TwedDTO twed = new TwedDTO( (Element) node );
        return twed;
    }

    @Override
    public Boolean whichIsRedundant(TwedDTO obj) {
        return obj.getWaterLevel().equals("");
    }

    @Override
    public void finalManipulate(TwedDTO obj) {
        System.out.println("test for not empty");
    }

    @Override
    public String getXML(String name) {
        return null;
    }
}

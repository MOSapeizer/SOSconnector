package sosconnector.DAO;

import org.w3c.dom.Node;
import sosconnector.DTO.TwedDTO;

/**
 * Created by zil on 2016/4/19.
 */
public class TwedDAO extends DAOFactory<TwedDTO> {

    public TwedDAO(String url) {
        super(url);
    }

    @Override
    public void initialize() {

    }

    @Override
    public TwedDTO map(Node node) {
        return null;
    }

    @Override
    public Boolean condition(TwedDTO obj) {
        return null;
    }

    @Override
    public void output(TwedDTO obj) {

    }


}

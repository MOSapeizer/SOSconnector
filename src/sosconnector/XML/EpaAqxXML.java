package sosconnector.XML;

/**
 * Created by zil on 2016/6/2.
 */
public class EpaAqxXML extends ObservationXML {
    @Override
    protected String setPrefix() {
        return null;
    }

    @Override
    protected String[] setProperties() {
        return new String[0];
    }

    @Override
    protected String setPropertyPrefix() {
        return null;
    }

    @Override
    protected String siteName() {
        return null;
    }

    @Override
    protected StringBuffer allObservations() {
        return null;
    }
}

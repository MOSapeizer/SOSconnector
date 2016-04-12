package sosconnector.Factory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import sosconnector.DBManager;
import sosconnector.DTO.EpaDTO;
import sosconnector.Request.InsertRequest;

/**
 * Created by zil on 2016/4/1.
 */
public class ObsFactory extends ResponseFactory<EpaDTO> {

    public ObsFactory(String source) {
        super(source);
    }

    @Override
    public Boolean whichIsRedundant(EpaDTO obj) {
        String siteName = obj.getSiteName();
        String publishTime = obj.getPublishTime();
        return (DBManager.getInstance().ifReadingRedundant("epa_aqx_reading", siteName, publishTime) != -1);
    }

    @Override
    public void initialize() {
        setDataName("Data");
    }

    @Override
    public EpaDTO operateNode(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            return new EpaDTO( (Element) node );
        }
        return null;
    }

    @Override
    public void finalManipulate(EpaDTO obj) {
        String siteName = obj.getSiteName();
        DBManager.getInstance().insertAQX_epa( obj );
        sendInsertObsRequest(epaURL, siteName, allObsInSite(obj));
    }

    public void sendInsertObsRequest(String url, String stationName, StringBuffer allObsString){
        String insertObservationXml = InsertRequest.getInsertObservationXml(stationName, allObsString);
        String response = sendInsertRequest(url, insertObservationXml);
        writeToDocumnet( response );
    }

    @Override
    public String getXML(String name) {
        return null;
    }

    public StringBuffer allObsInSite(EpaDTO obs){
        String[] latLon = latLon( obs.getSiteName() );
        StringBuffer allObsString = new StringBuffer();
        allObsString = allObsString.append( obs.observationXML("1", latLon, "PSI", obs.getPSI() ) );
        allObsString = allObsString.append(obs.observationXML("2", latLon, "SO2", obs.getSO2() ));
        allObsString = allObsString.append(obs.observationXML("3", latLon, "CO", obs.getCO() ));
        allObsString = allObsString.append(obs.observationXML("4", latLon, "O3", obs.getO3() ));
        allObsString = allObsString.append(obs.observationXML("5", latLon, "PM10", obs.getPM10() ));
        allObsString = allObsString.append(obs.observationXML("6", latLon, "PM2.5", obs.getPM2_5() ));
        allObsString = allObsString.append(obs.observationXML("7", latLon, "NO2", obs.getNO2() ));
        allObsString = allObsString.append(obs.observationXML("8", latLon, "WindSpeed", obs.getWindSpeed() ));
        allObsString = allObsString.append(obs.observationXML("9", latLon, "WindDirec", obs.getWindDirec() ));
        allObsString = allObsString.append(obs.observationXML("10", latLon, "FPMI", obs.getFPMI() ));
        allObsString = allObsString.append(obs.observationXML("11", latLon, "NOx", obs.getNOx() ));
        allObsString = allObsString.append(obs.observationXML("12", latLon, "NO", obs.getNO() ));
        return allObsString;
    }

    public String[] latLon(String siteName){
        String[] position = new String[2];
        position[0] = DBManager.getInstance().Getlon( siteName );
        position[1] = DBManager.getInstance().Getlat( siteName );
        return position;
    }
}

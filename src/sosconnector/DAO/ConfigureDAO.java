package sosconnector.DAO;

import sosconnector.Configure.*;
import sosconnector.DTO.*;
import sosconnector.DTO.Observation;
import sosconnector.Parser.DomParser;
import sosconnector.Parser.NodeParser;
import sosconnector.Parser.SourceParser;
import sosconnector.XML.XmlTemplate;
import sun.awt.image.ImageWatched;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by zil on 2016/6/16.
 */
public class ConfigureDAO {

    private final Configure configure;
    private LinkedList<LinkedHashMap> hashGroup;
    private SourceParser sourceParser;

    public ConfigureDAO(Configure configure, SourceParser sourceParser){
        this.sourceParser = sourceParser;
        this.configure = configure;
    }

    public XmlTemplate[] getXmlTemplateGroup(){
        int index = 0;
        reload();
        XmlTemplate[] xmlTemplates = new XmlTemplate[hashGroup.size()];
        for( LinkedHashMap hash : hashGroup )
            xmlTemplates[index++] = makeXmlTemplate(hash);
        return xmlTemplates;
    }

    public void reload(){
        DomParser domFromSource = createDomFromSource(sourceParser);
        this.hashGroup = new NodeParser( domFromSource, configure ).parse();
    }

    private XmlTemplate makeXmlTemplate(LinkedHashMap hash){
        String offering = (String) hash.get("offering");
        ObservationDTO observationDTO = makeObservationGroup(offering, hash, configure.getInsertObservation());
        SiteDTO siteDTO = makeSite(offering, configure.getInsertSensor());
        return new XmlTemplate(siteDTO, observationDTO);
    }

    private SiteDTO makeSite( String offering, InsertSensor insertSensor ){
        String prefix = insertSensor.getPrefix();
        String procedure = insertSensor.getProcedure();
        String propertyPrefix = transform( insertSensor.getPropertyPrefix() );
        String[] property = transform( insertSensor.getProperty()) ;
        return new SiteDTO( prefix, offering, procedure, propertyPrefix, property );
    }

    private String transform(Value value){
        return value.value;
    }

    private String[] transform(Value[] values){
        int index = 0;
        String[] ss = new String[values.length];
        for ( Value value : values )
           ss[index++] = value.value;
        return ss;
    }

    private sosconnector.DTO.Observation[] transform(LinkedHashMap hash, sosconnector.Configure.Observation[] observations){
        LinkedList<sosconnector.DTO.Observation> l = new LinkedList<>();
        for (sosconnector.Configure.Observation observation : observations)
            l.push( makeObservation(observation, hash) );
        return l.toArray(new Observation[l.size()]);
    }

    private sosconnector.DTO.Observation makeObservation(sosconnector.Configure.Observation observation, LinkedHashMap hash){
        String name = observation.name;
        String unit = observation.unit;
        String type = observation.type;
        String value = (String) hash.get(observation.name);
        String timestamp = (String) hash.get("timestamp");
        return new sosconnector.DTO.Observation(name, type, unit, value, timestamp);
    }

    private ObservationDTO makeObservationGroup(String offering, LinkedHashMap hash, InsertObservation insertObservation){
        String longitude = insertObservation.getLongitude();
        String latitude = insertObservation.getLatitude();
        sosconnector.DTO.Observation[] observations = transform(hash, insertObservation.getObservation());
        return new ObservationDTO(offering, longitude, latitude, observations);
    }

    private DomParser createDomFromSource(SourceParser sourceParser){
        String sourceFormGOV = sourceParser.getSourceFormGOV();
        return new DomParser(sourceFormGOV);
    }
}

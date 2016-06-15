package sosconnector.DTO;

/**
 * Created by zil on 2016/6/15.
 */
public class SiteDTO {
    private String offering;
    private String procedure;
    private String propertyPrefix;
    private String[] property;


    public SiteDTO(String offering, String procedure, String properyPrefix, String[] property) {
        this.offering = offering;
        this.procedure = procedure;
        this.propertyPrefix = properyPrefix;
        this.property = property;
    }

    public String getOffering() {
        return offering;
    }

    public String getProcedure() {
        return procedure;
    }

    public String getPropertyPrefix() {
        return propertyPrefix;
    }

    public String[] getProperty() {
        return property;
    }
}

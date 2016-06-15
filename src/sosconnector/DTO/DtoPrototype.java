package sosconnector.DTO;

/**
 * Created by zil on 2016/6/14.
 */
public class DtoPrototype {
    private String siteName;
    private String[] data;

    public DtoPrototype(String siteName, String[] data) {
        this.siteName = siteName;
        this.data = data;
    }

    public String getSiteName() {
        return siteName;
    }

    public String[] getData() {
        return data;
    }
}

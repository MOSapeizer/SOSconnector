import junit.framework.TestCase;
import Request.Request;

import java.io.IOException;

/**
 * Created by Zil on 2016/9/22.
 */
public class RequestTest extends TestCase {

    public void testGetRequest() throws IOException {
        Request request = new Request("http://cgis.csrsr.ncu.edu.tw:8080/swcb-sos-new/service");
        System.out.println(request.get());

    }

    public void testPsotRequest() throws IOException {
        Request request = new Request("http://cgis.csrsr.ncu.edu.tw:8080/swcb-sos-new/service");
        System.out.println(request.post("fuck"));
    }
}

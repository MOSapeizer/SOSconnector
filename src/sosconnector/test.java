/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sosconnector;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import sosconnector.Factory.ResponseFactory;
import sosconnector.Request.Request;

/**
 *
 * @author Sean
 */
public class test {

    private final static String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        String url = "http://opendata.epa.gov.tw/ws/Data/AQXSite/?$orderby=SiteName&$skip=0&$top=1000&format=xml";
        Request request = new Request( url );
        request.setConnection("GET");
        String response = request.getResponseBody();

        //Set
        ResponseFactory sir = new ResponseFactory<String>(response) {

            @Override
            public Boolean whichIsNotRedundant(String obj) {
                return true;
            }

            @Override
            public String make(Node node) {
                Element e = (Element) node;
                String sid = e.getElementsByTagName("TWD97Lon").item(0).getTextContent();
                return sid;
            }

            @Override
            public void finalManipulate(String obj) {
                System.out.println(obj);
            }

            @Override
            public String getXML(String name) {
                return null;
            }
        };

        sir.start();
    }
}

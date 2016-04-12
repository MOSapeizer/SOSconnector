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
 * @refactor Mos
 */
public class test {

    private final static String USER_AGENT = "Mozilla/5.0";
    public static void main(String[] args) throws Exception {

//        String url = "http://opendata.epa.gov.tw/ws/Data/AQXSite/?$orderby=SiteName&$skip=0&$top=1000&format=xml";
        String url = "http://data.wra.gov.tw/Service/OpenData.aspx?id=2D09DB8B-6A1B-485E-88B5-923A462F475C&format=xml";
//        Set Connection
        Request request = new Request( url );
        request.setConnection("GET");

        //Get response
        String response = request.getResponseBody();

        //Manipulate response
        ResponseFactory sir = new ResponseFactory<String>(response) {

            //Government data are all xml.
            //There is a transform for (xml) -> (obj).

            @Override
            public void initialize() {
                setDataName("twed:RealtimeWaterLevel_OPENDATA");
            }

            @Override
            public String operateNode(Node node) {
                Element e = (Element) node;
                String sid = e.getElementsByTagName("twed:WaterLevel").item(0).getTextContent();
                return sid;
            }

            //Obj filter.
            //determine which transformed nodes will go into the finalManipulate function.
            @Override
            public Boolean whichIsRedundant(String obj) {
                return false;
            }


            //Final step for the data
            //Suggestions
            //1. you can insert those into database.
            //2. output to the file.
            //3. just println.
            //4. do nothing.
            @Override
            public void finalManipulate(String obj) {
                System.out.println(obj);
            }


            //it is hard to explain, trace the code if you need.
            @Override
            public String getXML(String name) {
                return null;
            }
        };

        sir.start();


    }
}

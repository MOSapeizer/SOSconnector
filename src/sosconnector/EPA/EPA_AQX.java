/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sosconnector.EPA;
import sosconnector.Request.Request;
import sosconnector.SOSConnector;

import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hank
 * @refactor Mos
 */
public class EPA_AQX extends TimerTask {

    private final String SiteURL = "http://opendata.epa.gov.tw/ws/Data/AQXSite/?$orderby=SiteName&$skip=0&$top=1000&format=xml";
    private final String opendataURL = "http://opendata.epa.gov.tw/ws/Data/AQX/?$orderby=SiteName&$skip=0&$top=1000&format=xml";
    @Override
    public void run() {
        getAQXStationFromEPA();
        getAQXReadingFromEPA();
    }

    private void getAQXStationFromEPA() {
        try {
            String response = getResponseFromEPA( SiteURL );
            EpaSiteFactory epaSite = new EpaSiteFactory( response );

            epaSite.work();

        } catch (IOException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getAQXReadingFromEPA() {
        try {
            String response = getResponseFromEPA( opendataURL );
            EpaFactory epa = new EpaFactory( response );

            epa.work();

        } catch (IOException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getResponseFromEPA(String url) throws IOException {
        Request request = new Request( url );
        request.setConnection("GET");
        return request.getResponseBody();
    }

}

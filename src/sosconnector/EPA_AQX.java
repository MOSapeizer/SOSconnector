/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sosconnector;
import sosconnector.Factory.ObsFactory;
import sosconnector.Factory.SiteFactory;
import sosconnector.Request.Request;

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
            Request request = new Request( SiteURL );
            request.setConnection("GET");
            String response = request.getResponseBody();
            new ObsFactory( response ).insertDataIntoDatabase();
        } catch (IOException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getAQXReadingFromEPA() {
        try {

            Request request = new Request( opendataURL );
            request.setConnection("GET");
            String response = request.getResponseBody();
            new SiteFactory( response ).insertDataIntoDatabase();
        } catch (IOException ex) {
            Logger.getLogger(SOSConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

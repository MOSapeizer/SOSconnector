/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sosconnector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Alec Huang
 */
public class DBManager {

    private static DBManager instance = new DBManager();
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/connector_new";
    //  Database credentials
    private static final String USER = "root";
    private static final String PASSWORD = "1234567";

    private DBManager() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/connector_new?user=root&password=1234567&useUnicode=true&characterEncoding=utf-8"); 
//Connection conn = getConnection();
            //reset the database
            try {
                Statement statement = conn.createStatement();

                
//                statement.execute("CREATE TABLE IF NOT EXISTS station(sid int, name VARCHAR(50) CHARACTER SET utf8, en_name VARCHAR(50), lon double, lat double,district VARCHAR(50) CHARACTER SET utf8, debrisNO VARCHAR(50) CHARACTER SET utf8, disaster VARCHAR(50) CHARACTER SET utf8, river VARCHAR(50) CHARACTER SET utf8, drainage VARCHAR(50) CHARACTER SET utf8, catchment VARCHAR(50) CHARACTER SET utf8, subcatchment VARCHAR(50) CHARACTER SET utf8, en_district VARCHAR(80), en_debrisNO VARCHAR(50), en_disaster VARCHAR(50), en_river VARCHAR(50), en_drainage VARCHAR(50), en_catchment VARCHAR(50), en_subcatchment VARCHAR(50), imgURL VARCHAR(80), stationURL VARCHAR(80), primary key(sid));");
//                statement.execute("CREATE TABLE IF NOT EXISTS rain(stationname VARCHAR(50) CHARACTER SET utf8, name VARCHAR(50) CHARACTER SET utf8, lon VARCHAR(50), lat VARCHAR(50), primary key(stationname));");
//                statement.execute("CREATE TABLE IF NOT EXISTS reading(stationname VARCHAR(50) CHARACTER SET utf8, rtime VARCHAR(50) CHARACTER SET utf8, tenMin float, oneHr float, threeHr float, sixHr float, oneDay float, twentyfourHr float, twelveHr float, primary key(stationname,rtime));");

                statement.execute("CREATE TABLE IF NOT EXISTS swcb_station(sid int, name VARCHAR(50), en_name VARCHAR(50), lon double, lat double,district VARCHAR(50), debrisNO VARCHAR(50), disaster VARCHAR(50), river VARCHAR(50), drainage VARCHAR(50), catchment VARCHAR(50), subcatchment VARCHAR(50), en_district VARCHAR(80), en_debrisNO VARCHAR(50), en_disaster VARCHAR(50), en_river VARCHAR(50), en_drainage VARCHAR(50), en_catchment VARCHAR(50), en_subcatchment VARCHAR(50), imgURL VARCHAR(80), stationURL VARCHAR(80), primary key(sid));");
                statement.execute("CREATE TABLE IF NOT EXISTS swcb_rain(stationname VARCHAR(50), name VARCHAR(50), lon VARCHAR(50), lat VARCHAR(50), primary key(stationname));");
                statement.execute("CREATE TABLE IF NOT EXISTS swcb_reading(stationname VARCHAR(50), rtime VARCHAR(50), tenMin float, oneHr float, threeHr float, sixHr float, oneDay float, twentyfourHr float, twelveHr float, primary key(stationname,rtime));");
                statement.execute("CREATE TABLE IF NOT EXISTS epa_station(sid VARCHAR(50), stationname VARCHAR(50), county VARCHAR(50), township VARCHAR(50), unit VARCHAR(50), primary key(sid));");
                statement.execute("CREATE TABLE IF NOT EXISTS epa_rain(stationname VARCHAR(50), lon VARCHAR(50), lat VARCHAR(50), primary key(stationname));");
                statement.execute("CREATE TABLE IF NOT EXISTS epa_reading(stationname VARCHAR(50), rtime VARCHAR(50), tenMin float, oneHr float, threeHr float, sixHr float, twentyfourHr float, twelveHr float, now float, primary key(stationname,rtime));");
                
                statement.execute("CREATE TABLE IF NOT EXISTS epa_aqx_station(sid VARCHAR(50), SiteEngName VARCHAR(50), AreaName VARCHAR(50), county VARCHAR(50), township VARCHAR(50), SiteAddress VARCHAR(50), TWD97Lon VARCHAR(50), TWD97Lat VARCHAR(50), SiteType VARCHAR(50), primary key(sid));");
                statement.execute("CREATE TABLE IF NOT EXISTS epa_aqx_reading(stationname VARCHAR(50), rtime VARCHAR(50), County VARCHAR(50), PSI VARCHAR(50), MajorPollutant VARCHAR(50), Status VARCHAR(50), SO2 VARCHAR(50), CO VARCHAR(50), O3 VARCHAR(50), PM10 VARCHAR(50), PM2_5 VARCHAR(50), NO2 VARCHAR(50), WindSpeed VARCHAR(50), WindDirec VARCHAR(50), FPMI VARCHAR(50), NOx VARCHAR(50), NO VARCHAR(50), primary key(stationname));");
                
                statement.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Create DB Tables Error:" + ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DBManager getInstance() {
        return instance;
    }

    public void insertStation_swcb(String sid, String name, String en_name, String lon, String lat, String district, String debrisNO, String disaster, String river, String drainage, String catchment, String subcatchment, String en_district, String en_debrisNO, String en_disaster, String en_river, String en_drainage, String en_catchment, String en_subcatchment, String imgURL, String stationURL) {
        try {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/connector_new?user=root&password=1234567&useUnicode=true&characterEncoding=utf-8");
//            Connection conn = getConnection();
            Statement statement = conn.createStatement();
            statement.execute("Insert IGNORE into swcb_station values('" + sid  + "','" + name + "','" + en_name+ "','" + lon +"','" + lat +"','" + district +"','" + debrisNO +"','" + disaster +"','" + river +"','" + drainage +"','" + catchment +"','" + subcatchment +"','" + en_district +"','" + en_debrisNO +"','" + en_disaster +"','" + en_river +"','" + en_drainage +"','" + en_catchment +"','" + en_subcatchment +"','" + imgURL +"','" + stationURL + "');");
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertStation_epa(String sid, String stationname, String county, String township, String unit) {
        try {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/connector_new?user=root&password=1234567&useUnicode=true&characterEncoding=utf-8");
//            Connection conn = getConnection();
            Statement statement = conn.createStatement();
            statement.execute("Insert IGNORE into epa_station values('" + sid  + "','" + stationname + "','" + county+ "','" + township +"','" + unit + "');");
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertRain_swcb(String stationname, String name, String lon, String lat, String tenMin, String oneHr, String threeHr, String sixHr, String oneDay, String twentyfourHr, String rtime, String twelveHr) {
        try {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/connector_new?user=root&password=1234567&useUnicode=true&characterEncoding=utf-8");
//            Connection conn = getConnection();
            Statement statement = conn.createStatement();
            statement.execute("Insert IGNORE into swcb_rain values('" + stationname  + "','" + name + "','" + lon+ "','" + lat + "');");
            statement.execute("Insert IGNORE into swcb_reading values('" + stationname  +"','" + rtime +"','" + tenMin +"','" + oneHr +"','" + threeHr +"','" + sixHr +"','" + oneDay +"','" + twentyfourHr +"','" + twelveHr + "');");
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertRain_epa(String stationname, String lon, String lat, String tenMin, String oneHr, String threeHr, String sixHr, String now, String twentyfourHr, String rtime, String twelveHr) {
        try {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/connector_new?user=root&password=1234567&useUnicode=true&characterEncoding=utf-8");
//            Connection conn = getConnection();
            Statement statement = conn.createStatement();
            statement.execute("Insert IGNORE into epa_rain values('" + stationname + "','" + lon+ "','" + lat + "');");
            statement.execute("Insert IGNORE into epa_reading values('" + stationname  +"','" + rtime +"','" + tenMin +"','" + oneHr +"','" + threeHr +"','" + sixHr +"','" + twentyfourHr +"','" + twelveHr +"','" + now + "');");
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int ifStationRedundant(String table, String sid){
        int bool = -1;
        try {
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/connector_new?user=root&password=1234567&useUnicode=true&characterEncoding=utf-8");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT 1 FROM "+table+" WHERE sid=?");
            preparedStatement.setString(1, sid);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bool = rs.getInt(1);
            }

            preparedStatement.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    
    public int ifReadingRedundant(String table, String stationname, String rtime){
        int bool = -1;
        try {
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/connector_new?user=root&password=1234567&useUnicode=true&characterEncoding=utf-8");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT 1 FROM "+table+" WHERE stationname=? and rtime=?");
            preparedStatement.setString(1, stationname);
            preparedStatement.setString(2, rtime);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bool = rs.getInt(1);
            }

            preparedStatement.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }

    public void insertStation_epa_aqx(String SiteName, String SiteEngName, String AreaName, String County, String Township, String SiteAddress, String TWD97Lon, String TWD97Lat, String SiteType) {
        try {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/connector_new?user=root&password=1234567&useUnicode=true&characterEncoding=utf-8");
//            Connection conn = getConnection();
            Statement statement = conn.createStatement();
            statement.execute("Insert IGNORE into epa_aqx_station values('" + SiteName  + "','" + SiteEngName + "','" + AreaName+ "','" + County +"','" + Township +"','" + SiteAddress+"','" + TWD97Lon+"','" + TWD97Lat+"','" + SiteType+ "');");
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertAQX_epa(String SiteName, String County, String PSI, String MajorPollutant, String Status, String SO2, String CO, String O3, String PM10, String PM2_5, String NO2,String WindSpeed ,String WindDirec ,String FPMI ,String NOx ,String NO , String PublishTime) {
        try {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/connector_new?user=root&password=1234567&useUnicode=true&characterEncoding=utf-8");
//            Connection conn = getConnection();
            Statement statement = conn.createStatement();
            statement.execute("Insert IGNORE into epa_aqx_reading values('" + SiteName+ "','" + PublishTime  + "','" + County + "','" + PSI+ "','" + MajorPollutant +"','" + Status +"','" + SO2+"','" + CO+"','" + O3+"','" + PM10+"','" + PM2_5+"','" + NO2+"','" + WindSpeed+"','" + WindDirec+"','" + FPMI+"','" + NOx+"','" + NO+ "');");
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String Getlon(String stationname){
        
                
        String lon = null;
        try {
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/connector_new?user=root&password=1234567&useUnicode=true&characterEncoding=utf-8");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT TWD97Lon FROM epa_aqx_station WHERE sid=?");
            preparedStatement.setString(1, stationname);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                lon = rs.getString(1);
            }

            preparedStatement.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lon;
    }
    
    public String Getlat(String stationname){
        String lat = null;
        try {
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/connector_new?user=root&password=1234567&useUnicode=true&characterEncoding=utf-8");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT TWD97Lat FROM epa_aqx_station WHERE sid=?");
            preparedStatement.setString(1, stationname);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                lat = rs.getString(1);
            }

            preparedStatement.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lat;
    }
    
    private Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

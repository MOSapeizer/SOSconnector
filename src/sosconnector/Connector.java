package sosconnector;

import sosconnector.Adapter.SosFactory;
import sosconnector.DAO.DAOFactory;
import sosconnector.Configure.Configure;
import java.util.TimerTask;

/**
 * Created by zil on 2016/6/14.
 */
public class Connector extends TimerTask {

    private String filePath;
    private String service;
    private DAOFactory dao;

    public Connector(Configure configure) {
    }

    @Override
    public void run() {
        new SosFactory<>( dao, service ).work();
    }
}
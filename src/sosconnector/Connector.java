package sosconnector;

import sosconnector.Adapter.SosFactory;
import sosconnector.DAO.DAOFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.TimerTask;

/**
 * Created by zil on 2016/6/14.
 */
public class Connector extends TimerTask {

    private final String url;
    private final String filePath;
    private final String service;
    private final DAOFactory dao;

    public Connector(String url, String filePath, String service, DAOFactory dao) {
        this.url = url;
        this.filePath = filePath;
        this.service = service;
        this.dao = dao;
    }

    @Override
    public void run() {
        new SosFactory<>( dao, service ).work();
    }
}
package sosconnector;

import sosconnector.Adapter.SosFactory;
import sosconnector.DAO.DAOFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.TimerTask;

/**
 * Created by zil on 2016/6/14.
 */
public class ServiceFactory extends TimerTask {

    private final String url;
    private final String filePath;
    private final String service;
    private final Class DAO;

    public ServiceFactory(String url, String filePath, String service, Class dao) {
        this.url = url;
        this.filePath = filePath;
        this.service = service;
        this.DAO = dao;
    }

    @Override
    public void run() {
        DAOFactory daoInstance = (DAOFactory) createDaoInstance();
        new SosFactory<>( daoInstance, service ).work();
    }

    private Object createDaoInstance() {
        Constructor constructors = null;
        try {
            constructors = DAO.getConstructor(String.class, String.class);
            constructors.newInstance(url, filePath);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException Ignore) {
        }
        return constructors;
    }
}
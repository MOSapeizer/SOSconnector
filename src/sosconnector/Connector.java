package sosconnector;

import sosconnector.Adapter.SosFactory;
import sosconnector.DAO.ConfigureDAO;
import sosconnector.Configure.Configure;
import sosconnector.Configure.Info;
import sosconnector.DAO.SourceXmlDAO;
import sosconnector.Filter.TimerFilter;
import sosconnector.Parser.SourceParser;

import java.util.TimerTask;

/**
 * Created by zil on 2016/6/14.
 */
public class Connector extends TimerTask {

    private String service;
    private SourceXmlDAO dao;
    private TimerFilter timerFilter;

    public Connector(Configure configure) {
        service = configure.getInfo().getSos();
        dao = makeXmlDAO(configure);
        Info info = configure.getInfo();
        timerFilter = new TimerFilter(info.getPeriod());
    }

    @Override
    public void run() {
        new SosFactory( dao, service ).work();
    }

    private SourceXmlDAO makeXmlDAO(Configure configure){
        SourceParser sourceParser = new SourceParser(configure.getInfo());
        ConfigureDAO configureDAO = new ConfigureDAO(configure, sourceParser);
        return new SourceXmlDAO(configureDAO);
    }

    public int getPeriod(){
        return timerFilter.getPeriod();
    }
}
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
    private Info info;
    private TimerFilter timerFilter;
    private Configure configure;
    private ConfigureDAO configureDAO;

    public Connector(Configure configure) {
        this.configure = configure;
        this.info = configure.getInfo();
        this.service = info.getSos();
        this.timerFilter = new TimerFilter(info.getPeriod());
        this.configureDAO = createConfigureDAO();
        setPeriod(info);
    }

    @Override
    public void run() {
        dao = new SourceXmlDAO(configureDAO);
        new SosFactory( dao, service ).work();
    }

    private ConfigureDAO createConfigureDAO(){
        SourceParser sourceParser = new SourceParser(info);
        return new ConfigureDAO(configure, sourceParser);
    }

    public void setPeriod(Info info){
        this.timerFilter = new TimerFilter(info.getPeriod());
    }

    public int getPeriod(){
        return timerFilter.getPeriod();
    }
}
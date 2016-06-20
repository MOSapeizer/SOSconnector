package sosconnector;

import sosconnector.Adapter.SosFactory;
import sosconnector.Configure.Delegate;
import sosconnector.Configure.Configure;
import sosconnector.Configure.Info;
import sosconnector.DAO.XmlDAO;
import sosconnector.Filter.TimerFilter;
import sosconnector.Parser.SourceParser;

import java.util.TimerTask;

/**
 * Created by zil on 2016/6/14.
 */
public class Connector extends TimerTask {


    private String filePath;
    private String service;
    private XmlDAO dao;
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

    private XmlDAO makeXmlDAO(Configure configure){
        SourceParser sourceParser = new SourceParser(configure.getInfo());
        Delegate delegate = new Delegate(configure, sourceParser);
        return new XmlDAO( delegate );
    }

    public int getPeriod(){
        return timerFilter.getPeriod();
    }
}
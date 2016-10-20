package Connector;

import Request.SosRequest;

import java.io.IOException;
import java.util.TimerTask;

/**
 * Created by Zil on 2016/10/12.
 */
public abstract class Connector extends TimerTask {

    protected SosRequest sosRequest;

    public Connector(String sosUrl) throws IOException {
        sosRequest = new SosRequest(sosUrl);
    }

    public abstract void run();
}

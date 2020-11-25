package src.main.java.remoting;

import src.main.java.config.Configurable;
import src.main.java.remoting.life.LifeCycle;

/**
 * @InterfaceName RemotingServer
 * @Description remoting server
 * @Author lidong
 * @Date 2020/11/22
 * @Version 1.0
 */
public interface RemotingServer extends Configurable, LifeCycle {

    /**
     * Get the ip of the server.
     *
     * @return ip
     */
    String ip();

    /**
     * Get the port of the server.
     *
     * @return listened port
     */
    int port();
}

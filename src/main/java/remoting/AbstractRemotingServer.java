package src.main.java.remoting;

import src.main.java.config.ConfigType;
import src.main.java.remoting.life.AbstractLifeCycle;

import java.net.InetSocketAddress;

/**
 * @ClassName AbstractRemotingServer
 * @Description Abstract Remoting Server
 * @Author lidong
 * @Date 2020/11/24
 * @Version 1.0
 */
public abstract class AbstractRemotingServer extends AbstractLifeCycle implements RemotingServer {

    private String ip;

    private int port;

    private ConfigType configType;

    public AbstractRemotingServer(int port) {
        this(new InetSocketAddress(port).getAddress().getHostAddress(), port);
    }

    public AbstractRemotingServer(String ip, int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException(String.format("Illegal port value: [%d], which should between 0 and 65535", port));
        }
        this.ip = ip;
        this.port = port;

        this.configType = ConfigType.SERVER_SIDE;
    }

}

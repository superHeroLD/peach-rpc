package src.main.java.peach.rpc.remoting.server;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.SneakyThrows;

import java.net.InetAddress;

/**
 * @ClassName RpcServer
 * @Description rpc server
 * @Author lidong
 * @Date 2020/12/1
 * @Version 1.0
 */
public class RpcServer {

    public static final int DEFAULT_PORT = 9998;

    @SneakyThrows
    public void start() {
        String host = InetAddress.getLocalHost().getHostAddress();

        //TODO 这以后都改成读取配置
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();


    }
}

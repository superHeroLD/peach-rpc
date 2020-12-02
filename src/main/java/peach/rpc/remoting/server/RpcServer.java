package src.main.java.peach.rpc.remoting.server;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import lombok.SneakyThrows;
import peach.rpc.util.ThreadPoolFactoryUtil;

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
        DefaultEventExecutorGroup serviceHandlerGroup = new DefaultEventExecutorGroup(
                Runtime.getRuntime().availableProcessors() * 2,
                ThreadPoolFactoryUtil.buildThreadFactory("service-handler-group", false));

    }
}

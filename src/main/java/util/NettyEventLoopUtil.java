package src.main.java.util;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ThreadFactory;

/**
 * @ClassName NettyEventLoopUtil
 * @Description Netty eventLoop Util
 * @Author lidong
 * @Date 2020/11/22
 * @Version 1.0
 */
public class NettyEventLoopUtil {

    /**
     * 看系统是否支持epoll
     */
    private static final boolean EPOLL_ENABLED = Epoll.isAvailable();


    public static EventLoopGroup newEventLoopGroup(int nThreads, ThreadFactory threadFactory) {
        return EPOLL_ENABLED ? new EpollEventLoopGroup(nThreads, threadFactory)
                : new NioEventLoopGroup(nThreads, threadFactory);
    }


    public static Class<? extends SocketChannel> getClientSocketChannelClass() {
        return EPOLL_ENABLED ? EpollSocketChannel.class : NioSocketChannel.class;
    }


    public static Class<? extends ServerSocketChannel> getServerSocketChannelClass() {
        return EPOLL_ENABLED ? EpollServerSocketChannel.class : NioServerSocketChannel.class;
    }


    public static void enableTriggeredMode(ServerBootstrap serverBootstrap) {
        if (EPOLL_ENABLED) {
            //边缘触发
            serverBootstrap.childOption(EpollChannelOption.EPOLL_MODE, EpollMode.EDGE_TRIGGERED);
        }
    }
}

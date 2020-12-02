package peach.rpc.remoting.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import peach.rpc.remoting.NamedThreadFactory;
import peach.rpc.remoting.codec.RpcMessageDecoder;
import peach.rpc.remoting.codec.RpcMessageEncoder;
import peach.rpc.util.NettyEventLoopUtil;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RpcServer
 * @Description rpc server
 * @Author lidong
 * @Date 2020/12/1
 * @Version 1.0
 */
@Slf4j
public class RpcServer {

    public static final int DEFAULT_PORT = 9998;

    private static final EventLoopGroup BOSS_GROUP = NettyEventLoopUtil.newEventLoopGroup(1,
            new NamedThreadFactory("Rpc-netty-server-boss", false));

    private static final EventLoopGroup WORKER_GROUP = NettyEventLoopUtil.newEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2,
            new NamedThreadFactory("Rpc-netty-server-worker", true));

    @SneakyThrows
    public void start() {
        String host = InetAddress.getLocalHost().getHostAddress();

        //TODO 这以后都改成读取配置
        DefaultEventExecutorGroup serviceHandlerGroup = new DefaultEventExecutorGroup(
                Runtime.getRuntime().availableProcessors() * 2, new NamedThreadFactory("service-handler-group", false));

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(BOSS_GROUP, WORKER_GROUP)
                    .channel(NettyEventLoopUtil.getServerSocketChannelClass())
                    // TCP默认开启了 Nagle 算法，该算法的作用是尽可能的发送大数据快，减少网络传输。TCP_NODELAY 参数的作用就是控制是否启用 Nagle 算法。
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    // 是否开启 TCP 底层心跳机制
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //表示系统用于临时存放已完成三次握手的请求的队列的最大长度,如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 当客户端第一次进行请求的时候才会进行初始化
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            // 30 秒之内没有收到客户端请求的话就关闭连接
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new IdleStateHandler(30, 0, 0, TimeUnit.SECONDS));
                            p.addLast(new RpcMessageEncoder());
                            p.addLast(new RpcMessageDecoder());
//                            p.addLast(serviceHandlerGroup, new NettyRpcServerHandler());
                        }
                    });

            // 绑定端口，同步等待绑定成功
            ChannelFuture f = b.bind(host, DEFAULT_PORT).sync();
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("occur exception when start server:", e);
        } finally {
            log.error("shutdown bossGroup and workerGroup");
            BOSS_GROUP.shutdownGracefully();
            WORKER_GROUP.shutdownGracefully();
            serviceHandlerGroup.shutdownGracefully();
        }
    }
}

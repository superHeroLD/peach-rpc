package peach.rpc.registry;

import java.net.InetSocketAddress;

/**
 * 服务注册接口
 *
 * @author lidong
 * @date 2020/12/6
 */
public interface ServiceRegistry {

    /**
     * register service
     *
     * @param rpcServiceName    rpc service name
     * @param inetSocketAddress service address
     */
    void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress);
}

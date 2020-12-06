package peach.rpc.registry;

import java.net.InetSocketAddress;

/**
 * @InterfaceName ServiceRegistry
 * @Description server registration interface
 * @Author lidong
 * @Date 2020/12/6
 * @Version 1.0
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

package peach.rpc.registry;

import java.net.InetSocketAddress;

/**
 * @InterfaceName ServiceDiscovery
 * @Description 服务发现
 * @Author lidong
 * @Date 2020/12/10
 * @Version 1.0
 */
public interface ServiceDiscovery {

    /**
     * 服务发现
     *
     * @param rpcServiceName 服务名称
     * @return IP地址
     */
    InetSocketAddress lookupService(String rpcServiceName);
}

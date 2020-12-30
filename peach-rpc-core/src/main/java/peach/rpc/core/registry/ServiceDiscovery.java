package peach.rpc.core.registry;

import java.net.InetSocketAddress;

/**
 * 服务发现接口
 *
 * @author lidong
 * @date 2020/12/10
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

package peach.rpc.registry.memory;

import peach.rpc.registry.ServiceDiscovery;

import java.net.InetSocketAddress;

/**
 * 内存服务发现（仅仅测试使用）
 *
 * @author lidong
 * @date 2020/12/10
 */
public class MemoryServiceDiscovery implements ServiceDiscovery {

    @Override
    public InetSocketAddress lookupService(String rpcServiceName) {
        InetSocketAddress inetSocketAddress = MemoryServiceRegistry.getServiceRegistryMap().get(rpcServiceName);

        if (null == inetSocketAddress) {
            throw new IllegalStateException(String.format("Can't find rpcServiceName: [%s]", rpcServiceName));
        }

        return inetSocketAddress;
    }
}

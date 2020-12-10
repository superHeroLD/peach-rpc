package peach.rpc.registry.memory;

import peach.rpc.registry.ServiceDiscovery;

import java.net.InetSocketAddress;

/**
 * @ClassName MemoryServiceDiscovery
 * @Description 内存服务发现
 * @Author lidong
 * @Date 2020/12/10
 * @Version 1.0
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

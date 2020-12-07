package peach.rpc.registry;

import lombok.extern.slf4j.Slf4j;
import peach.rpc.util.StringUtil;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName MemoryServiceRegistry
 * @Description 内存注册中心(只是测试时用)
 * @Author lidong
 * @Date 2020/12/7
 * @Version 1.0
 */
@Slf4j
public class MemoryServiceRegistry implements ServiceRegistry {

    private static final Map<String, InetSocketAddress> serviceRegistryMap = new ConcurrentHashMap<>();

    @Override
    public void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress) {
        if (StringUtil.isBlank(rpcServiceName)) {
            throw new RuntimeException("rpcServiceName is null");
        }

        if (null == inetSocketAddress) {
            throw new RuntimeException("inetSocketAddress is null");
        }

        if (serviceRegistryMap.containsKey(rpcServiceName)) {
            log.info("[MemoryServiceRegistry] already exists rpcServiceName: {} in registry", rpcServiceName);
            return;
        }

        serviceRegistryMap.put(rpcServiceName, inetSocketAddress);
    }
}

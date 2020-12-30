package peach.rpc.core.registry.memory;

import lombok.extern.slf4j.Slf4j;
import peach.rpc.core.registry.ServiceRegistry;
import peach.rpc.core.util.StringUtil;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存服务注册，就是测试用的
 *
 * @author lidong
 * @date 2020/12/7
 */
@Slf4j
public class MemoryServiceRegistry implements ServiceRegistry {

    private static final Map<String, InetSocketAddress> SERVICE_REGISTRY_MAP = new ConcurrentHashMap<>();

    @Override
    public void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress) {
        if (StringUtil.isBlank(rpcServiceName)) {
            throw new RuntimeException("rpcServiceName is null");
        }

        if (null == inetSocketAddress) {
            throw new RuntimeException("inetSocketAddress is null");
        }

        if (SERVICE_REGISTRY_MAP.containsKey(rpcServiceName)) {
            log.info("[MemoryServiceRegistry] already exists rpcServiceName: {} in registry", rpcServiceName);
            return;
        }

        SERVICE_REGISTRY_MAP.put(rpcServiceName, inetSocketAddress);
    }

    public static Map<String, InetSocketAddress> getServiceRegistryMap() {
        return SERVICE_REGISTRY_MAP;
    }
}

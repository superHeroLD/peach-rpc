package peach.rpc.remoting.provider;

import peach.rpc.remoting.dto.RpcServiceProperties;

/**
 * @InterfaceName ServiceProvider
 * @Description 服务Provider抽象类
 * @Author lidong
 * @Date 2020/12/6
 * @Version 1.0
 */
public interface ServiceProvider {

    /**
     * @param service              service object
     * @param serviceClass         the interface class implemented by the service instance object
     * @param rpcServiceProperties service related attributes
     */
    void addService(Object service, Class<?> serviceClass, RpcServiceProperties rpcServiceProperties);

    /**
     * @param rpcServiceProperties service related attributes
     * @return service object
     */
    Object getService(RpcServiceProperties rpcServiceProperties);

    /**
     * @param service              service object
     * @param rpcServiceProperties service related attributes
     */
    void publishService(Object service, RpcServiceProperties rpcServiceProperties);

    /**
     * @param service service object
     */
    void publishService(Object service);
}

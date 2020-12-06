package peach.rpc.remoting.provider;

import lombok.extern.slf4j.Slf4j;
import peach.rpc.remoting.dto.RpcServiceProperties;

/**
 * @ClassName ServiceProviderImpl
 * @Description TODO
 * @Author lidong
 * @Date 2020/12/6
 * @Version 1.0
 */
@Slf4j
public class ServiceProviderImpl implements ServiceProvider {


    @Override
    public void addService(Object service, Class<?> serviceClass, RpcServiceProperties rpcServiceProperties) {

    }

    @Override
    public Object getService(RpcServiceProperties rpcServiceProperties) {
        return null;
    }

    @Override
    public void publishService(Object service, RpcServiceProperties rpcServiceProperties) {

    }

    @Override
    public void publishService(Object service) {

    }
}

package peach.rpc.core.remoting.handler;

import lombok.extern.slf4j.Slf4j;
import peach.rpc.core.exception.RpcException;
import peach.rpc.core.factory.SingletonFactory;
import peach.rpc.core.remoting.dto.RpcRequest;
import peach.rpc.core.remoting.provider.ServiceProvider;
import peach.rpc.core.remoting.provider.ServiceProviderImpl;

import java.lang.reflect.Method;

/**
 * RPC request handler
 *
 * @author lidong
 * @date 2020/12/8
 */
@Slf4j
public class RpcRequestHandler {

    private final ServiceProvider serviceProvider;

    public RpcRequestHandler() {
        serviceProvider = SingletonFactory.getInstance(ServiceProviderImpl.class);
    }

    public Object handle(RpcRequest rpcRequest) {
        Object service = serviceProvider.getService(rpcRequest.toRpcProperties());
        return invokeTargetMethod(rpcRequest, service);
    }

    /**
     * get method execution results
     *
     * @param rpcRequest client request
     * @param service    service object
     * @return the result of the target method execution
     */
    private Object invokeTargetMethod(RpcRequest rpcRequest, Object service) {
        Object result;
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            result = method.invoke(service, rpcRequest.getParameters());
            log.info("service:[{}] successful invoke method:[{}]", rpcRequest.getInterfaceName(), rpcRequest.getMethodName());
        } catch (Exception e) {
            throw new RpcException(e.getMessage(), e);
        }
        return result;
    }
}

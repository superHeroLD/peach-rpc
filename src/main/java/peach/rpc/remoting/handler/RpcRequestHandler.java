package peach.rpc.remoting.handler;

import lombok.extern.slf4j.Slf4j;
import peach.rpc.exception.RpcException;
import peach.rpc.factory.SingletonFactory;
import peach.rpc.remoting.dto.RpcRequest;
import peach.rpc.remoting.provider.ServiceProvider;
import peach.rpc.remoting.provider.ServiceProviderImpl;

import java.lang.reflect.Method;

/**
 * @ClassName RpcRequestHandler
 * @Description TODO
 * @Author lidong
 * @Date 2020/12/8
 * @Version 1.0
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

package peach.rpc.remoting.transport;

import peach.rpc.remoting.dto.RpcRequest;

/**
 * @ClassName RpcRequestTransport
 * @Description send rpc request
 * @Author lidong
 * @Date 2020/12/10
 * @Version 1.0
 */
public interface RpcRequestTransport {

    /**
     * send rpc request to server and get result
     *
     * @param rpcRequest message body
     * @return data from server
     */
    Object sendRpcRequest(RpcRequest rpcRequest);
}

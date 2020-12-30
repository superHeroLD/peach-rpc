package peach.rpc.core.remoting.transport;

import peach.rpc.core.remoting.dto.RpcRequest;

/**
 * @author lidong
 * @date 2020/12/10
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

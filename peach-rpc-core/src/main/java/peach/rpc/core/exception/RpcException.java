package peach.rpc.core.exception;


import peach.rpc.core.constant.enums.RpcErrorMessageEnum;

/**
 * @ClassName RpcException
 * @Description Rpc异常
 * @Author lidong
 * @Date 2020/12/7
 * @Version 1.0
 */
public class RpcException extends RuntimeException {

    public RpcException(RpcErrorMessageEnum rpcErrorMessageEnum, String detail) {
        super(rpcErrorMessageEnum.getMessage() + ":" + detail);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(RpcErrorMessageEnum rpcErrorMessageEnum) {
        super(rpcErrorMessageEnum.getMessage());
    }
}

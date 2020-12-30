package peach.rpc.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @EnumName RpcErrorMessageEnum
 * @Description TODO
 * @Author lidong
 * @Date 2020/12/7
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum RpcErrorMessageEnum {

    /**
     *
     */
    CLIENT_CONNECT_SERVER_FAILURE("客户端连接服务端失败"),
    SERVICE_INVOCATION_FAILURE("服务调用失败"),
    SERVICE_CAN_NOT_BE_FOUND("没有找到指定的服务"),
    SERVICE_NOT_IMPLEMENT_ANY_INTERFACE("注册的服务没有实现任何接口"),
    REQUEST_NOT_MATCH_RESPONSE("返回结果错误！请求和返回的相应不匹配");

    private final String message;
}

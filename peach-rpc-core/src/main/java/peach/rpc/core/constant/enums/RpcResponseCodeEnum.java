package peach.rpc.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @EnumName RpcResponseCodeEnum
 * @Description response code
 * @Author lidong
 * @Date 2020/12/2
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum RpcResponseCodeEnum {

    /**
     * response code
     */
    SUCCESS(200, "The remote call is successful"),
    FAIL(500, "The remote call is fail");

    private final int code;

    private final String message;
}

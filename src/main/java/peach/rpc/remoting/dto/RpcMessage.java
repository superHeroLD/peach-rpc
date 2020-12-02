package peach.rpc.remoting.dto;

import lombok.*;

/**
 * @ClassName RpcMessage
 * @Description rpc message
 * @Author lidong
 * @Date 2020/12/2
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RpcMessage {

    /**
     * rpc message type
     */
    private byte messageType;

    /**
     * serialization type
     */
    private byte codec;

    /**
     * compress type
     */
    private byte compress;

    /**
     * request id
     */
    private int requestId;

    /**
     * request data
     */
    private Object data;
}

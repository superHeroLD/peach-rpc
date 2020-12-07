package peach.rpc.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName RpcConstant
 * @Description some constant
 * @Author lidong
 * @Date 2020/12/2
 * @Version 1.0
 */
public class RpcConstant {

    /**
     * Magic number. Verify RpcMessage
     */
    public static final byte[] MAGIC_NUMBER = {'l', 'y', 'z', 'p'};
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    /**
     * version
     */
    public static final byte VERSION = 1;
    public static final byte TOTAL_LENGTH = 16;
    public static final byte REQUEST_TYPE = 1;
    public static final byte RESPONSE_TYPE = 2;

    /**
     * ping
     */
    public static final byte HEARTBEAT_REQUEST_TYPE = 3;

    /**
     * pong
     */
    public static final byte HEARTBEAT_RESPONSE_TYPE = 4;
    public static final int HEAD_LENGTH = 16;
    public static final String PING = "ping";
    public static final String PONG = "pong";
    public static final int MAX_FRAME_LENGTH = 8 * 1024 * 1024;

    /**
     * 下划线分隔符
     */
    public static final String UNDER_SCORE = "_";
}

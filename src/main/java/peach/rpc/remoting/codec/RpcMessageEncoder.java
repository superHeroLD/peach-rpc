package peach.rpc.remoting.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import peach.rpc.remoting.dto.RpcMessage;

/**
 * @ClassName RpcMessageEncoder
 * @Description *
 * <p>
 * custom protocol decoder
 * <p>
 * <pre>
 *    0     1     2     3     4        5     6     7     8         9          10      11     12  13  14   15 16
 *     +-----+-----+-----+-----+--------+----+----+----+------+-----------+-------+----- --+-----+-----+-------+
 *     |   magic   code        |version | full length         | messageType| codec|compress|    RequestId       |
 *     +-----------------------+--------+---------------------+-----------+-----------+-----------+------------+
 *     |                                                                                                       |
 *     |                                         body                                                          |
 *     |                                                                                                       |
 *     |                                        ... ...                                                        |
 *     +-------------------------------------------------------------------------------------------------------+
 *   4B  magic code（魔法数）   1B version（版本）   4B full length（消息长度）    1B messageType（消息类型）
 *   1B compress（压缩类型） 1B codec（序列化类型）    4B  requestId（请求的Id）
 *   body（object类型数据）
 *   </pre>
 * @Author lidong
 * @Date 2020/12/2
 * @Version 1.0
 */
@Slf4j
public class RpcMessageEncoder extends MessageToByteEncoder<RpcMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcMessage msg, ByteBuf out) throws Exception {

    }
}

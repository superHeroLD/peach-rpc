package peach.rpc.core.remoting.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import peach.rpc.core.constant.RpcConstant;
import peach.rpc.core.constant.enums.CompressTypeEnum;
import peach.rpc.core.constant.enums.SerializationTypeEnum;
import peach.rpc.core.remoting.compress.Compress;
import peach.rpc.core.remoting.compress.gzip.GzipCompress;
import peach.rpc.core.remoting.dto.RpcMessage;
import peach.rpc.core.remoting.serialize.Serializer;
import peach.rpc.core.remoting.serialize.kryo.KryoSerializer;

import java.util.concurrent.atomic.AtomicInteger;

/**
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
 *   5B  magic code（魔法数）   1B version（版本）   4B full length（消息长度）    1B messageType（消息类型）
 *   1B compress（压缩类型） 1B codec（序列化类型）    4B  requestId（请求的Id）
 *   body（object类型数据）
 *   </pre>
 *
 * @Author lidong
 * @Date 2020/12/2
 * @Version 1.0
 */
@Slf4j
public class RpcMessageEncoder extends MessageToByteEncoder<RpcMessage> {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcMessage rpcMessage, ByteBuf out) throws Exception {
        try {
            out.writeBytes(RpcConstant.MAGIC_NUMBER);
            out.writeByte(RpcConstant.VERSION);

            // leave a place to write the value of full length
            out.writerIndex(out.writerIndex() + 4);
            byte messageType = rpcMessage.getMessageType();
            out.writeByte(messageType);
            out.writeByte(rpcMessage.getCodec());
            out.writeByte(CompressTypeEnum.GZIP.getCode());
            out.writeInt(ATOMIC_INTEGER.getAndIncrement());
            // build full length
            byte[] bodyBytes = null;
            int fullLength = RpcConstant.HEAD_LENGTH;
            // if messageType is not heartbeat message,fullLength = head length + body length
            if (messageType != RpcConstant.HEARTBEAT_REQUEST_TYPE
                    && messageType != RpcConstant.HEARTBEAT_RESPONSE_TYPE) {
                // serialize the object
                String codecName = SerializationTypeEnum.getName(rpcMessage.getCodec());
                log.info("codec name: [{}] ", codecName);
                //TODO 这里要改成配置的
                Serializer serializer = new KryoSerializer();
                bodyBytes = serializer.serialize(rpcMessage.getData());
                // compress the bytes
                //TODO 这里要改成配置的
                Compress compress = new GzipCompress();
                bodyBytes = compress.compress(bodyBytes);
                fullLength += bodyBytes.length;
            }

            if (bodyBytes != null) {
                out.writeBytes(bodyBytes);
            }
            int writeIndex = out.writerIndex();
            out.writerIndex(writeIndex - fullLength + RpcConstant.MAGIC_NUMBER.length + 1);
            out.writeInt(fullLength);
            out.writerIndex(writeIndex);
        } catch (Exception e) {
            log.error("[RpcMessageEncoder] encode request occur error!", e);
        }
    }
}

package peach.rpc.core.remoting.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;
import peach.rpc.core.constant.RpcConstant;
import peach.rpc.core.remoting.compress.Compress;
import peach.rpc.core.remoting.compress.gzip.GzipCompress;
import peach.rpc.core.remoting.dto.RpcMessage;
import peach.rpc.core.remoting.dto.RpcRequest;
import peach.rpc.core.remoting.dto.RpcResponse;
import peach.rpc.core.remoting.serialize.Serializer;
import peach.rpc.core.remoting.serialize.kryo.KryoSerializer;

import java.util.Arrays;

import static peach.rpc.core.constant.RpcConstant.MAX_FRAME_LENGTH;

/**
 * custom protocol decoder
 * <pre>
 *   0     1     2     3     4        5     6     7     8         9          10      11     12  13  14   15 16
 *   +-----+-----+-----+-----+--------+----+----+----+------+-----------+-------+----- --+-----+-----+-------+
 *   |   magic   code        |version | full length         | messageType| codec|compress|    RequestId       |
 *   +-----------------------+--------+---------------------+-----------+-----------+-----------+------------+
 *   |                                                                                                       |
 *   |                                         body                                                          |
 *   |                                                                                                       |
 *   |                                        ... ...                                                        |
 *   +-------------------------------------------------------------------------------------------------------+
 * 4B  magic code（魔法数）   1B version（版本）   4B full length（消息长度）    1B messageType（消息类型）
 * 1B compress（压缩类型） 1B codec（序列化类型）    4B  requestId（请求的Id）
 * body（object类型数据）
 * </pre>
 * <p>
 * {@link LengthFieldBasedFrameDecoder} is a length-based decoder , used to solve TCP unpacking and sticking problems.
 * </p>
 *
 * @see <a href="https://zhuanlan.zhihu.com/p/95621344">LengthFieldBasedFrameDecoder解码器</a>
 */
@Slf4j
public class RpcMessageDecoder extends LengthFieldBasedFrameDecoder {

    public RpcMessageDecoder() {
        super(MAX_FRAME_LENGTH, 4, 4, -9, 0);
    }

    public RpcMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
                             int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        Object decoded = super.decode(ctx, in);
        if (decoded instanceof ByteBuf) {
            ByteBuf frame = (ByteBuf) decoded;
            if (frame.readableBytes() >= RpcConstant.TOTAL_LENGTH) {
                try {
                    return decodeFrame(frame);
                } catch (Exception e) {
                    log.error("Decode frame error!", e);
                    throw e;
                } finally {
                    frame.release();
                }
            }

        }
        return decoded;
    }


    private Object decodeFrame(ByteBuf in) {
        // note: must read ByteBuf in order
        // read the first 4 bit, which is the magic number, and compare
        int len = RpcConstant.MAGIC_NUMBER.length;
        byte[] tmp = new byte[len];
        in.readBytes(tmp);
        for (int i = 0; i < len; i++) {
            if (tmp[i] != RpcConstant.MAGIC_NUMBER[i]) {
                throw new IllegalArgumentException("Unknown magic code: " + Arrays.toString(tmp));
            }
        }
        // read the version and compare
        byte version = in.readByte();
        if (version != RpcConstant.VERSION) {
            throw new RuntimeException("version isn't compatible" + version);
        }
        int fullLength = in.readInt();
        // build RpcMessage object
        byte messageType = in.readByte();
        byte codecType = in.readByte();
        byte compressType = in.readByte();
        int requestId = in.readInt();
        RpcMessage rpcMessage = RpcMessage.builder()
                .codec(codecType)
                .requestId(requestId)
                .messageType(messageType).build();
        if (messageType == RpcConstant.HEARTBEAT_REQUEST_TYPE) {
            rpcMessage.setData(RpcConstant.PING);
        } else if (messageType == RpcConstant.HEARTBEAT_RESPONSE_TYPE) {
            rpcMessage.setData(RpcConstant.PONG);
        } else {
            int bodyLength = fullLength - RpcConstant.HEAD_LENGTH;
            if (bodyLength > 0) {
                byte[] bs = new byte[bodyLength];
                in.readBytes(bs);
                // decompress the bytes
                Compress compress = new GzipCompress();
                bs = compress.decompress(bs);
                // deserialize the object
                Serializer serializer = new KryoSerializer();
                if (messageType == RpcConstant.REQUEST_TYPE) {
                    RpcRequest tmpValue = serializer.deserialize(bs, RpcRequest.class);
                    rpcMessage.setData(tmpValue);
                } else {
                    RpcResponse tmpValue = serializer.deserialize(bs, RpcResponse.class);
                    rpcMessage.setData(tmpValue);
                }
            }
        }
        return rpcMessage;

    }

}

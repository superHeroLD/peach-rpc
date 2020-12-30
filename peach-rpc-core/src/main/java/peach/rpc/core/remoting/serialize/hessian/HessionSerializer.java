package peach.rpc.core.remoting.serialize.hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import peach.rpc.core.exception.SerializeException;
import peach.rpc.core.remoting.serialize.Serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @ClassName HessionSerializer
 * @Description Hession 序列化
 * @Author lidong
 * @Date 2020/12/9
 * @Version 1.0
 */
public class HessionSerializer implements Serializer {

    @Override
    public byte[] serialize(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(byteArrayOutputStream);
        try {
            hessian2Output.writeObject(obj);

            // 必须先关闭，才能转成二进制数组
            hessian2Output.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new SerializeException(e.getMessage());
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                throw new SerializeException(e.getMessage());
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        Hessian2Input hessian2Input = new Hessian2Input(byteArrayInputStream);
        try {
            return (T) hessian2Input.readObject(clazz);
        } catch (IOException e) {
            throw new SerializeException(e.getMessage());
        } finally {
            try {
                hessian2Input.close();
                byteArrayInputStream.close();
            } catch (IOException e) {
                throw new SerializeException(e.getMessage());
            }
        }
    }
}

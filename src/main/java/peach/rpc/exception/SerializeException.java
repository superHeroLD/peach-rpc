package peach.rpc.exception;

/**
 * @ClassName SerializeException
 * @Description Serialize Exception
 * @Author lidong
 * @Date 2020/12/2
 * @Version 1.0
 */
public class SerializeException extends RuntimeException {

    public SerializeException(String message) {
        super(message);
    }
}

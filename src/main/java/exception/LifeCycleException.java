package src.main.java.exception;

/**
 * @ClassName LifeCycleException
 * @Description server life cycle exception
 * @Author lidong
 * @Date 2020/11/22
 * @Version 1.0
 */
public class LifeCycleException extends RuntimeException {

    private static final long serialVersionUID = 792388352995752889L;

    public LifeCycleException(String message) {
        super(message);
    }


}

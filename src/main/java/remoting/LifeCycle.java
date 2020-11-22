package src.main.java.remoting;

import src.main.java.exception.LifeCycleException;

/**
 * @InterfaceName LifeCycle
 * @Description server Life Cycle interface
 * @Author lidong
 * @Date 2020/11/22
 * @Version 1.0
 */
public interface LifeCycle {

    /**
     * 服务器启动
     *
     * @throws LifeCycleException 异常
     */
    void startup() throws LifeCycleException;

    /**
     * 服务器关闭
     *
     * @throws LifeCycleException LifeCycleException
     */
    void shutdown() throws LifeCycleException;

    /**
     * 服务器是否启动
     *
     * @return 是否启动
     */
    boolean isStarted();
}

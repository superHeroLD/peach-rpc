package peach.rpc.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @InterfaceName DefaultThreadPoolConfig
 * @Description 默认线程池配置
 * @Author lidong
 * @Date 2020/12/1
 * @Version 1.0
 */
public class DefaultThreadPoolConfig implements IThreadPoolConfig{

    /**
     * 默认线程池参数
     */
    private static final int DEFAULT_CORE_POOL_SIZE = 10;
    private static final int DEFAULT_MAXIMUM_POOL_SIZE_SIZE = 100;
    private static final int DEFAULT_KEEP_ALIVE_TIME = 1;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MINUTES;
    private static final int DEFAULT_BLOCKING_QUEUE_CAPACITY = 100;

    @Override
    public int corePoolSize() {
        return DEFAULT_CORE_POOL_SIZE;
    }

    @Override
    public int maxCorePoolSize() {
        return DEFAULT_MAXIMUM_POOL_SIZE_SIZE;
    }

    @Override
    public int keepAliveTime() {
        return DEFAULT_KEEP_ALIVE_TIME;
    }

    @Override
    public TimeUnit keepAliveTimeUnit() {
        return DEFAULT_TIME_UNIT;
    }

    @Override
    public int blockingQueueCapacity() {
        return DEFAULT_BLOCKING_QUEUE_CAPACITY;
    }

    @Override
    public BlockingQueue<Runnable> taskQueue() {
        return new ArrayBlockingQueue<>(DEFAULT_BLOCKING_QUEUE_CAPACITY);
    }
}

package config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SingleThreadPoolConfig
 * @Description 但线程
 * @Author lidong
 * @Date 2020/12/1
 * @Version 1.0
 */
public class SingleThreadPoolConfig implements IThreadPoolConfig {
    @Override
    public int corePoolSize() {
        return 1;
    }

    @Override
    public int maxCorePoolSize() {
        return 1;
    }

    @Override
    public int keepAliveTime() {
        return 1;
    }

    @Override
    public TimeUnit keepAliveTimeUnit() {
        return TimeUnit.MINUTES;
    }

    @Override
    public int blockingQueueCapacity() {
        return 100;
    }

    @Override
    public BlockingQueue<Runnable> taskQueue() {
        return new ArrayBlockingQueue<>(blockingQueueCapacity());
    }
}

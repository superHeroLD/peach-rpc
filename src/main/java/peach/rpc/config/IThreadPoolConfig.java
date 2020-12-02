package peach.rpc.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName IThreadPoolConfig
 * @Description 线程池配置
 * @Author lidong
 * @Date 2020/12/1
 * @Version 1.0
 */
public interface IThreadPoolConfig {

    /**
     * 系统可用CPU数量
     */
    int SYSTEM_AVAILABLE_PROCESSOR_NUM = Runtime.getRuntime().availableProcessors();

    /**
     * 默认任务队列数量
     */
    int DEFAULT_BLOCKING_QUEUE_CAPACITY = 100;

    /**
     * 获取核心线程数量
     */
    int corePoolSize();

    /**
     * 最大核心线程数量
     */
    int maxCorePoolSize();

    /**
     * 线程存活时间
     */
    int keepAliveTime();

    /**
     * 线程存活时间单位
     */
    TimeUnit keepAliveTimeUnit();

    /**
     * 任务队列数量
     */
    int blockingQueueCapacity();

    /**
     * 任务队列
     */
    BlockingQueue<Runnable> taskQueue();

    /**
     * 获取默认任务队列
     */
    default BlockingQueue<Runnable> getDefaultWorkQueue() {
        return new ArrayBlockingQueue<>(DEFAULT_BLOCKING_QUEUE_CAPACITY);
    }
}

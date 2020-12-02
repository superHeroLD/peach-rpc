package peach.rpc.util;

import lombok.extern.slf4j.Slf4j;
import peach.rpc.config.IThreadPoolConfig;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName ThreadPoolFactoryUtil
 * @Description create ThreadPool util
 * @Author lidong
 * @Date 2020/12/1
 * @Version 1.0
 */
@Slf4j
public class ThreadPoolFactoryUtil {

    private static final Map<String, ExecutorService> THREAD_POOLS = new ConcurrentHashMap<>();

    private ThreadPoolFactoryUtil() {
    }

    public static ExecutorService createCustomThreadPoolIfAbsent(IThreadPoolConfig threadPoolConfig, String threadNamePrefix, Boolean daemon) {
        ExecutorService threadPool = THREAD_POOLS.computeIfAbsent(threadNamePrefix, k -> createThreadPool(threadPoolConfig, threadNamePrefix, daemon));
        // 如果 threadPool 被 shutdown 的话就重新创建一个
        if (threadPool.isShutdown() || threadPool.isTerminated()) {
            THREAD_POOLS.remove(threadNamePrefix);
            threadPool = createThreadPool(threadPoolConfig, threadNamePrefix, daemon);
            THREAD_POOLS.put(threadNamePrefix, threadPool);
        }
        return threadPool;
    }

    /**
     * 根据配置创建线程池
     *
     * @param threadPoolConfig 线程池配置
     * @param threadNamePrefix 线程名称前缀
     * @param daemon           是否是守护线程
     * @return 线程池
     */
    private static ExecutorService createThreadPool(IThreadPoolConfig threadPoolConfig, String threadNamePrefix, Boolean daemon) {
        ThreadFactory threadFactory = buildThreadFactory(threadNamePrefix, daemon);
        return new ThreadPoolExecutor(threadPoolConfig.corePoolSize(), threadPoolConfig.maxCorePoolSize(),
                threadPoolConfig.keepAliveTime(), threadPoolConfig.keepAliveTimeUnit(), threadPoolConfig.taskQueue(),
                threadFactory);
    }

    /**
     * 构建线程工厂
     *
     * @param threadNamePrefix 线程名称前缀
     * @param daemon           是否是守护线程
     * @return 线程工厂
     */
    public static synchronized ThreadFactory buildThreadFactory(String threadNamePrefix, boolean daemon) {
        if (StringUtil.isNotBlank(threadNamePrefix)) {
            final AtomicLong count = new AtomicLong(0);

            return r -> {
                Thread thread = Executors.defaultThreadFactory().newThread(r);
                thread.setName(String.format(threadNamePrefix + "-%d", count.incrementAndGet()));
                thread.setDaemon(daemon);
                return thread;
            };
        }

        return Executors.defaultThreadFactory();
    }

    /**
     * 打印线程池的状态
     *
     * @param threadPool 线程池对象
     */
    public static void printThreadPoolStatus(ThreadPoolExecutor threadPool) {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, buildThreadFactory("monitor-thread-pool", true));
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("============ThreadPool Status=============");
            log.info("ThreadPool Size: [{}]", threadPool.getPoolSize());
            log.info("Active Threads: [{}]", threadPool.getActiveCount());
            log.info("Number of Tasks : [{}]", threadPool.getCompletedTaskCount());
            log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());
            log.info("===========================================");
        }, 0, 1, TimeUnit.MINUTES);
    }
}

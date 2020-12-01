package peach.rpc.util;

import config.IThreadPoolConfig;
import lombok.extern.slf4j.Slf4j;

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
    private static synchronized ThreadFactory buildThreadFactory(String threadNamePrefix, boolean daemon) {
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
}

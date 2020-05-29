package com.gene.cyptomonitoringtools.monitor;

import com.gene.cyptomonitoringtools.monitor.scheduler.PriceMonitoringScheduleThread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author quzhe
 * @date 2020/3/25 15:38
 * @description 自驱启动类
 */
@Component
@Slf4j
public class SchedulerStartServer {
    /**
     * bucketMoveScheduler thread pool
     */
    private ScheduledExecutorService priceMonitoringScheduleService;

    @Resource
    private PriceMonitoringScheduleThread priceMonitoringScheduleThread;




    @PostConstruct
    public void run() {


        //SIResourceScheduler
        priceMonitoringScheduleService = this.newThreadScheduledExecutor("SIResource", 1, false);
        //SIResourceSchedulerThread siResourceSchedulerThread = new SIResourceSchedulerThread();
        priceMonitoringScheduleService.scheduleAtFixedRate(priceMonitoringScheduleThread, 0L, 1000L, TimeUnit.MILLISECONDS);
    }


    /**
     * Wrapper over newSingleThreadExecutor.
     *
     * @param threadName
     * @return
     */
    private ExecutorService newDaemonSingleThreadExecutor(String threadName) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setDaemon(true)
            .setNameFormat(threadName)
            .build();
        return Executors.newSingleThreadExecutor(threadFactory);
    }

    /**
     * Wrapper over ScheduledThreadPoolExecutor
     *
     * @param threadName
     * @param corePoolSize
     * @param isDaemon
     * @return
     */
    private ScheduledExecutorService newThreadScheduledExecutor(String threadName, int corePoolSize, boolean isDaemon) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setDaemon(isDaemon)
            .setNameFormat(threadName)
            .build();
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(corePoolSize, threadFactory);
        // By default, a cancelled task is not automatically removed from the work queue until its delay
        // elapses. We have to enable it manually.
        executor.setRemoveOnCancelPolicy(true);
        return executor;
    }
}

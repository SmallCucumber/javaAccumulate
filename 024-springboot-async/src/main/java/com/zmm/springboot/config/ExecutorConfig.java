package com.zmm.springboot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@Data
public class ExecutorConfig {

    /**
     * 核心线程
     */
    @Value("${spring.task.execution.pool.core-size}")
    private int corePoolSize;

    /**
     * 最大线程
     */
    @Value("${spring.task.execution.pool.max-size}")
    private int maxPoolSize;

    /**
     * 队列容量
     */
    @Value("${spring.task.execution.pool.queue-capacity}")
    private int queueCapacity;

    /**
     * 保持时间
     */
    @Value("${spring.task.execution.pool.keep-alive}")
    private int keepAliveSeconds;

    /**
     * 名称前缀
     */
    @Value("${spring.task.execution.thread-name-prefix}")
    private String preFix;

    @Bean("myExecutor")
    public ThreadPoolTaskExecutor myExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix(preFix);
        executor.setRejectedExecutionHandler( new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;
    }
}

package com.springboot.config;

import com.springboot.constant.CacheConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;

import java.time.Duration;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：zz
 * @date ：Created in 2022/5/19 10:23
 * @description：消费组配置
 */
@Configuration
public class RedisStreamConfiguration {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer() {
        AtomicInteger index = new AtomicInteger(1);
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(processors, processors, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(), r -> {
            Thread thread = new Thread(r);
            thread.setName("async-stream-consumer-" + index.getAndIncrement());
            thread.setDaemon(true);
            return thread;
        });

        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> options =
                StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                        .builder()
                        // 一次最多获取多少条消息
                        .batchSize(5)
                        // 运行 Stream 的 poll task
                        .executor(executor)
                        // Stream 中没有消息时，阻塞多长时间，需要比 `spring.redis.timeout` 的时间小
                        .pollTimeout(Duration.ofSeconds(3))
                        // 获取消息的过程或获取到消息给具体的消息者处理的过程中，发生了异常的处理
                        .errorHandler(new StreamErrorHandler())
                        .build();

        StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer =
                StreamMessageListenerContainer.create(redisConnectionFactory, options);

        // 独立消费
        String streamKey = CacheConstant.REDIS_STEAM_01;
        /*streamMessageListenerContainer.receive(StreamOffset.fromStart(streamKey),
                new ConsumeListener("独立消费", null, null));*/

        // 消费组A,不自动ack
        // 从消费组中没有分配给消费者的消息开始消费
        streamMessageListenerContainer.receive(Consumer.from("group-a", "consumer-a"),
                StreamOffset.create(streamKey, ReadOffset.lastConsumed()), new ConsumeListener1(/*"消费组消费", "group-a", "consumer-a"*/));
        /*// 从消费组中没有分配给消费者的消息开始消费
        streamMessageListenerContainer.receive(Consumer.from("group-a", "consumer-b"),
                StreamOffset.create(streamKey, ReadOffset.lastConsumed()), new ConsumeListener1(*//*"消费组消费A", "group-a", "consumer-b"*//*));*/

        // 消费组B,自动ack
        streamMessageListenerContainer.receiveAutoAck(Consumer.from("group-b", "consumer-a"),
                StreamOffset.create(streamKey, ReadOffset.lastConsumed()), new ConsumeListener(/*"消费组消费B", "group-b", "consumer-bb"*/));

        return streamMessageListenerContainer;
    }

}

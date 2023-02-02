package com.zmm.springboot.config;

import com.zmm.springboot.annotation.MeiceRedisStreamListener;
import com.zmm.springboot.bean.MeiceUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;


/**
 * 利用redis原生消息队列实现
 * Copyright © 2021 meicet. All rights reserved.
 * @author zyx
 * @date 2021-06-21 09:49:11
*/
@Slf4j
@Configuration
@Component
public class RedisConsumerConfig implements DisposableBean {


	@Resource
	private ApplicationContext context;

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	private Vector<StreamMessageListenerContainer<String, ObjectRecord<String, MeiceUser>>> containerList = new Vector<>();

	@Resource
	ForkJoinPool forkJoinPool;

	@Resource
	RedisConnectionFactory factory;

	@Bean(name = "forkJoinPool")
	public ExecutorService forkJoinPool() {
		return new ForkJoinPool();
	}

	@PostConstruct
	public void initRedisStream() throws Exception {
		Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(MeiceRedisStreamListener.class);
		if (beansWithAnnotation.size() == 0) {
			return;
		}
		for (Object item : beansWithAnnotation.values()) {
			if (!(item instanceof StreamListener)) {
				continue;
			}
			Method method = item.getClass().getDeclaredMethod("onMessage", Record.class);

			MeiceRedisStreamListener annotation = method.getAnnotation(MeiceRedisStreamListener.class);
			if (annotation == null) {
				continue;
			}
			creasteSubscription(factory, (StreamListener) item, annotation.streamKey(), annotation.consumerGroup(), annotation.consumerName());
		}
	}

	private void creatGroup(String key, String group) {
		StreamOperations<String, String, MeiceUser> streamOperations = this.stringRedisTemplate.opsForStream();
		String groupName = streamOperations.createGroup(key, group);
		log.info("creatGroup:{}", groupName);
	}

	private Subscription creasteSubscription(RedisConnectionFactory factory, StreamListener streamListener, String streamKey, String group, String consumerName) {
		StreamOperations<String, String, MeiceUser> streamOperations = this.stringRedisTemplate.opsForStream();

		if (stringRedisTemplate.hasKey(streamKey)) {
			StreamInfo.XInfoGroups groups = streamOperations.groups(streamKey);
			if (groups.isEmpty()) {
				creatGroup(streamKey, group);
			} else {
				groups.stream().forEach(g -> {
					log.info("XInfoGroups:{}", g);
					StreamInfo.XInfoConsumers consumers = streamOperations.consumers(streamKey, g.groupName());
					log.info("XInfoConsumers:{}", consumers);
				});
			}
		} else {
			creatGroup(streamKey, group);
		}


		StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, ObjectRecord<String, MeiceUser>> options =
				StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder()
				                                                                    .batchSize(10)
				                                                                    .serializer(new StringRedisSerializer())
				                                                                    .executor(forkJoinPool)
				                                                                    .pollTimeout(Duration.ZERO)
				                                                                    .targetType(MeiceUser.class)
				                                                                    .build();

		StreamMessageListenerContainer<String, ObjectRecord<String, MeiceUser>> listenerContainer = StreamMessageListenerContainer.create(factory, options);
		StreamOffset<String> streamOffset = StreamOffset.create(streamKey, ReadOffset.lastConsumed());

		Consumer consumer = Consumer.from(group, consumerName);

		Subscription subscription = listenerContainer.receive(consumer, streamOffset, streamListener);
		listenerContainer.start();
		this.containerList.add(listenerContainer);
		return subscription;
	}


	@Override
	public void destroy() {
		this.containerList.forEach(StreamMessageListenerContainer::stop);
	}
}

package com.zmm.springboot.config;

import com.zmm.springboot.annotation.MeiceListener;
import com.zmm.springboot.annotation.RedisDelayQueueListener;
import com.zmm.springboot.utils.RedisLock;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 延迟队列执行器
 * Copyright © 2020 meicet. All rights reserved.
 *
 * @author zyx
 * @date 2020-10-21 10:58:29
 */
@Slf4j
@Component
public class RedisDelayQueueExecutors implements InitializingBean {

	@Resource
	RedisLock redisLock;

	@Resource
	private ApplicationContext context;

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	private AtomicBoolean isClose = new AtomicBoolean(false);

	private ExecutorService executorService;

	@SneakyThrows
	@Override
	public void afterPropertiesSet() {
		Map<String, MeiceListener> beanMap = context.getBeansOfType(MeiceListener.class);
		if (beanMap.size() == 0) {
			return;
		}
		executorService = Executors.newFixedThreadPool(beanMap.size());
		for (MeiceListener consumer : beanMap.values()) {
			Method method = consumer.getClass().getDeclaredMethod("onMessage", Object.class);
			RedisDelayQueueListener listener = method.getAnnotation(RedisDelayQueueListener.class);
			if (listener == null) {
				continue;
			}
			String queue = listener.queue();
			log.info("延迟消息队列名称:{}", queue);

			executorService.execute(() -> {
				while (!isClose.get()) {
					try {
						Set<ZSetOperations.TypedTuple<Object>> typedTuples = redisTemplate.opsForZSet().rangeWithScores(queue, 0, 0);

						if (typedTuples == null || typedTuples.isEmpty()) {
							TimeUnit.MILLISECONDS.sleep(500);
							continue;
						}

						Iterator<ZSetOperations.TypedTuple<Object>> iterator = typedTuples.iterator();
						while (iterator.hasNext()) {
							ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();

							Object value = typedTuple.getValue();
							Double score = typedTuple.getScore();

							long now = System.currentTimeMillis();
							if (now >= score) {
								try {
									if (redisLock.lock(queue, score.toString())) {
										consumer.onMessage(value);

										//消费完成后将该值移出zset
										redisTemplate.opsForZSet().remove(queue, value);
									}
								} finally {
									redisLock.unlock(queue, score.toString());
								}
							}
						}

					} catch (Exception e) {
						log.error("延迟队列{}读取消息失败:{}", queue, e);
					}
				}
			});
		}
	}


	@PreDestroy
	public void preDestory() {
		isClose.set(true);
		executorService.shutdown();
	}

}
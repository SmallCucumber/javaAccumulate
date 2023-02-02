package com.zmm.springboot.config;

import com.zmm.springboot.annotation.MeiceListener;
import com.zmm.springboot.annotation.RedisListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;


/**
 * 利用redis rightPop实现消息队列
 * Copyright © 2021 meicet. All rights reserved.
 * @author zyx
 * @date 2021-06-21 09:43:37
*/
@Slf4j
@Component
public class RedisExecutors implements DisposableBean {

	@Resource
	private ApplicationContext context;

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	private boolean isClose = false;

	@Resource
	ForkJoinPool forkJoinPool;

	@SneakyThrows
	@PostConstruct
	public void init() {
		Map<String, MeiceListener> beanMap = context.getBeansOfType(MeiceListener.class);
		if (beanMap.size() == 0) {
			return;
		}
		for (MeiceListener item : beanMap.values()) {
			Method method = item.getClass().getDeclaredMethod("onMessage", Object.class);
			RedisListener listener = method.getAnnotation(RedisListener.class);
			if (listener == null) {
				continue;
			}
			String queue = listener.queue();
			log.info("消息队列名称:{}", queue);

			forkJoinPool.execute(() -> {
				long timeout = listener.timeout();
				while (!isClose) {
					try {
						Object message = stringRedisTemplate.opsForList().rightPop(queue, timeout, TimeUnit.SECONDS);
						if (message == null) {
							continue;
						}
						item.onMessage(message);
					} catch (Exception e) {
						log.error("队列{}读取消息失败:{}", queue, e);
						try {
							TimeUnit.MILLISECONDS.sleep(1000);
						} catch (InterruptedException e1) {
							log.error("sleep失败:", e1);
						}
					}
				}
			});
		}
	}

	@Override
	public void destroy() {
		isClose = true;
		forkJoinPool.shutdown();
	}
}
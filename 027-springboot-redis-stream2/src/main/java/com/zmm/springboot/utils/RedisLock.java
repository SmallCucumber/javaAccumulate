package com.zmm.springboot.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisLock {

	@Resource
	private RedisTemplate<String, String> redisTemplate;

	private final static String LOCK_KEY = "meice:lock:";

	/**
	 * 加锁
	 *
	 * @param key   锁定的编号
	 * @param value 设置锁定的时间
	 */
	public boolean lock(String key, String value) {
		return lock(key, value, 60L);
	}

	public boolean lock(String key, String value, Long lockSeconds) {
		// 如果key值不存在，则返回 true，且设置 value
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		Boolean bool = valueOperations.setIfAbsent(LOCK_KEY + key + ":" + value, value, lockSeconds, TimeUnit.SECONDS);

		return bool != null && bool;
	}


	/**
	 * 解锁
	 *
	 * @param key   锁定的编号
	 * @param value 设置锁定的时间
	 */
	public void unlock(String key, String value) {
		try {
			ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
			String curVal = valueOperations.get(LOCK_KEY + key + ":" + value);
			if (!StringUtils.isEmpty(curVal) && curVal.equals(value)) {
				valueOperations.getOperations().delete(LOCK_KEY + key + ":" + value);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
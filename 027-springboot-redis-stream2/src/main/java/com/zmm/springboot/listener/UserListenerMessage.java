package com.zmm.springboot.listener;

import cn.hutool.core.date.DateUtil;
import com.zmm.springboot.annotation.MeiceRedisStreamListener;
import com.zmm.springboot.bean.MeiceUser;
import com.zmm.springboot.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@MeiceRedisStreamListener
@Component
@Slf4j
public class UserListenerMessage implements StreamListener<String, ObjectRecord<String, MeiceUser>> {
	@Resource
	private StringRedisTemplate stringRedisTemplate;


	@MeiceRedisStreamListener(streamKey = RedisConstant.STREAM_KEY, consumerGroup = RedisConstant.STREAM_GROUP, consumerName = RedisConstant.CONSUMER_NAME)
	@Override
	public void onMessage(ObjectRecord<String, MeiceUser> message) {
		System.err.println("stream:" + DateUtil.current());
		stringRedisTemplate.opsForStream().acknowledge(RedisConstant.STREAM_GROUP, message);
		MeiceUser value = message.getValue();
		System.err.println(value);
	}
}

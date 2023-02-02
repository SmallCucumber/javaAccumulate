package com.zmm.springboot.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.zmm.springboot.bean.MeiceUser;
import com.zmm.springboot.constant.RedisConstant;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MsgController {
	@Resource
	private StringRedisTemplate stringRedisTemplate;


	@GetMapping(value = "/{age}")
	public String setKey(@PathVariable Integer age) {
		MeiceUser user = new MeiceUser();
		user.setAge(age);
		user.setName("张三");
		user.setDate(DateUtil.current());
		ObjectRecord<String, MeiceUser> stringUserObjectRecord = ObjectRecord.create(RedisConstant.STREAM_KEY, user);
		RecordId recordId = stringRedisTemplate.opsForStream().add(stringUserObjectRecord);

		stringRedisTemplate.opsForList().leftPush(RedisConstant.QUEUE_MSG, new JSONObject(user).toString());

		return new JSONObject(recordId).toString();
	}


}

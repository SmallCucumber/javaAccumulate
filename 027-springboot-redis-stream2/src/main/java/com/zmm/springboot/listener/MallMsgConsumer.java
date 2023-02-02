package com.zmm.springboot.listener;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.zmm.springboot.annotation.MeiceListener;
import com.zmm.springboot.annotation.RedisListener;
import com.zmm.springboot.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MallMsgConsumer implements MeiceListener {


	/**
	 * 统一消息发送
	 *
	 * @param o 微信消息模版
	 */
	@RedisListener(queue = RedisConstant.QUEUE_MSG)
	@Override
	public void onMessage(Object o) {
		System.err.println("prop:" + DateUtil.current());

		System.err.println(new JSONObject(o).toString());
	}

}

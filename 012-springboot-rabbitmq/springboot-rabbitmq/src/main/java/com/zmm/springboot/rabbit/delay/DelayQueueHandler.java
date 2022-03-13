package com.zmm.springboot.rabbit.delay;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.zmm.springboot.rabbit.RabbitConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 * 延迟队列处理器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-01-04 17:42
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitConsts.DELAY_QUEUE)
public class DelayQueueHandler {

    @RabbitHandler
    public void directHandlerManualAck(String messageStruct) {
            log.info("延迟队列，手动ACK，接收消息：{}", JSONUtil.toJsonStr(messageStruct));
    }
}

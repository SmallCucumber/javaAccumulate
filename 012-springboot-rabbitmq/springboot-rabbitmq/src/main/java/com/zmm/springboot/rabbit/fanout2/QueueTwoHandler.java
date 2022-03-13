package com.zmm.springboot.rabbit.fanout2;

import com.rabbitmq.tools.json.JSONUtil;
import com.zmm.springboot.rabbit.RabbitConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 * 队列2 处理器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-01-04 15:42
 */
@Slf4j
@RabbitListener(queues = RabbitConsts.QUEUE_TWO)
@Component
public class QueueTwoHandler {

    @RabbitHandler
    public void directHandlerAutoAck(String message) {
        log.info("QueueTwoHandler直接队列处理器，接收消息：{}", message);
    }

}

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
 * 直接队列1 处理器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-01-04 15:42
 */
@Slf4j
@RabbitListener(queues = RabbitConsts.DIRECT_MODE_QUEUE_ONE)
@Component
public class DirectQueueOneHandler {

    /**
     * 如果 spring.rabbitmq.listener.direct.acknowledge-mode: auto，则可以用这个方式，会自动ack
     */
    @RabbitHandler
    public void directHandlerAutoAck(String message) {
        log.info("DirectQueueOneHandler 直接队列处理器，接收消息：{}", message);
    }

}

package com.zmm.springboot.rabbit.delay;

import cn.hutool.core.date.DateUtil;
import com.zmm.springboot.rabbit.RabbitConsts;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DelayProd {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDelay() {
        rabbitTemplate.convertAndSend(RabbitConsts.DELAY_MODE_QUEUE, RabbitConsts.DELAY_QUEUE, "delay message, delay 5s, " + DateUtil.date(), message -> {
            message.getMessageProperties().setHeader("x-delay", 5000);
            return message;
        });
        rabbitTemplate.convertAndSend(RabbitConsts.DELAY_MODE_QUEUE, RabbitConsts.DELAY_QUEUE, "delay message,  delay 2s, " + DateUtil.date(), message -> {
            message.getMessageProperties().setHeader("x-delay", 2000);
            return message;
        });
        rabbitTemplate.convertAndSend(RabbitConsts.DELAY_MODE_QUEUE, RabbitConsts.DELAY_QUEUE, "delay message,  delay 8s, " + DateUtil.date(), message -> {
            message.getMessageProperties().setHeader("x-delay", 8000);
            return message;
        });
    }
}

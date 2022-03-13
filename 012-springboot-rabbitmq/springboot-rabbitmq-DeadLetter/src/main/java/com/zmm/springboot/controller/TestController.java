package com.zmm.springboot.controller;

import com.zmm.springboot.config.DLXRabbitMQConfig;
import com.zmm.springboot.config.DelayedRabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/demo")
@Slf4j
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("testRabbitMQ")
    public void testRabbitMQ(@RequestParam String msg, @RequestParam Integer delayTime) {
        log.info("当前时间：{},收到请求，msg:{},delayTime:{}", new Date(), msg, delayTime);
        rabbitTemplate.convertAndSend(DelayedRabbitMQConfig.DELAYED_EXCHANGE_NAME, DelayedRabbitMQConfig.DELAYED_ROUTING_KEY, msg, a -> {
            a.getMessageProperties().setDelay(delayTime);
            return a;
        });
    }

    @GetMapping("sendmsg")
    public void sendMsg(String msg){
        log.info("当前时间：{},收到请求，msg:{}", new Date(), msg);
        rabbitTemplate.convertSendAndReceive(DLXRabbitMQConfig.BUSINESS_EXCHANGE_NAME, "", msg);
    }

}

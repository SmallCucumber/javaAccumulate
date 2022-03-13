package com.zmm.springboot.controller;

import cn.hutool.core.date.DateUtil;
import com.zmm.springboot.constants.RabbitConsts;
import com.zmm.springboot.message.MessageStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/demo")
@Slf4j
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @GetMapping("sendmsg")
    public void sendMsg(){
        log.info("当前时间：{},收到请求", new Date());
        {
            rabbitTemplate.convertAndSend(RabbitConsts.DELAY_MODE_QUEUE, RabbitConsts.DELAY_QUEUE, new MessageStruct("delay message, delay 5s, " + DateUtil.date()), message -> {
                System.out.println("convertAndSend:5000");
                message.getMessageProperties().setHeader("x-delay", 5000);
                return message;
            });
            rabbitTemplate.convertAndSend(RabbitConsts.DELAY_MODE_QUEUE, RabbitConsts.DELAY_QUEUE, new MessageStruct("delay message,  delay 2s, " + DateUtil.date()), message -> {
                System.out.println("convertAndSend:2000");
                message.getMessageProperties().setHeader("x-delay", 2000);
                return message;
            });
            rabbitTemplate.convertAndSend(RabbitConsts.DELAY_MODE_QUEUE, RabbitConsts.DELAY_QUEUE, new MessageStruct("delay message,  delay 8s, " + DateUtil.date()), message -> {
                System.out.println("convertAndSend:8000");
                message.getMessageProperties().setHeader("x-delay", 8000);
                return message;
            });
        }
    }

}

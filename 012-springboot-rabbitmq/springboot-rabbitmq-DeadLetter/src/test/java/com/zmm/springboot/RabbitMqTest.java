package com.zmm.springboot;

import com.zmm.springboot.config.DLXRabbitMQConfig;
import com.zmm.springboot.config.DelayedRabbitMQConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testRabbitMQ() {
        String msg="testRabbitMQ";
        Integer delayTime=2;

        System.out.println("当前时间：{"+new Date()+"},收到请求，msg:{"+msg+"},delayTime:{}"+  delayTime);
        rabbitTemplate.convertAndSend(DelayedRabbitMQConfig.DELAYED_EXCHANGE_NAME, DelayedRabbitMQConfig.DELAYED_ROUTING_KEY, msg, a -> {
            a.getMessageProperties().setDelay(delayTime);
            return a;
        });
    }

    @Test
    public void sendMsg(){
        String msg="sendMsg";
        rabbitTemplate.convertSendAndReceive(DLXRabbitMQConfig.BUSINESS_EXCHANGE_NAME, "", msg);
    }
}

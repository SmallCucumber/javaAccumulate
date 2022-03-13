package com.zmm.springboot.rabbit.fanout;

import com.zmm.springboot.rabbit.RabbitConsts;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String context = "hi, fanout msg ";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("fanoutExchange","", context);
	}

    public void send2() {
		String context = "hi, fanout msg222222222 ";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend(RabbitConsts.FANOUT_MODE_QUEUE,"", context);
    }
}
package com.zmm.springboot.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig2 {

    /**
     * 队列3
     */
    @Bean
    public Queue queueThree() {
        return new Queue(RabbitConsts.QUEUE_THREE);
    }


    @Bean
    public TopicExchange topicExchange2() {
        return new TopicExchange(RabbitConsts.TOPIC_MODE_QUEUE);
    }


    /**
     * 主题模式绑定分列模式
     *
     * @param fanoutExchange 分列模式交换器
     * @param topicExchange  主题模式交换器
     */
    @Bean
    public Binding topicBinding1(FanoutExchange fanoutExchange2, TopicExchange topicExchange2) {
        return BindingBuilder.bind(fanoutExchange2).to(topicExchange2).with(RabbitConsts.TOPIC_ROUTING_KEY_ONE);
    }

    /**
     * 主题模式绑定队列2
     *
     * @param queueTwo      队列2
     * @param topicExchange 主题模式交换器
     */
    @Bean
    public Binding topicBinding2(Queue queueTwo, TopicExchange topicExchange2) {
        return BindingBuilder.bind(queueTwo).to(topicExchange2).with(RabbitConsts.TOPIC_ROUTING_KEY_TWO);
    }

    /**
     * 主题模式绑定队列3
     *
     * @param queueThree    队列3
     * @param topicExchange 主题模式交换器
     */
    @Bean
    public Binding topicBinding3(Queue queueThree, TopicExchange topicExchange2) {
        return BindingBuilder.bind(queueThree).to(topicExchange2).with(RabbitConsts.TOPIC_ROUTING_KEY_THREE);
    }
}

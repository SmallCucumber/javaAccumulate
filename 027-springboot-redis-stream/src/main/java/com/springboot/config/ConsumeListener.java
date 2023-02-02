package com.springboot.config;

import com.springboot.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author ：zz
 * @date ：Created in 2022/5/19 10:15
 * @description：消费监听
 */
@Slf4j
@Component
public class ConsumeListener implements StreamListener<String, MapRecord<String, String, String>> {

    @Autowired
    private RedisUtil redisUtil;

    private static ConsumeListener consumeListener;

    @PostConstruct
    public void init(){
        consumeListener = this;
        consumeListener.redisUtil=this.redisUtil;
    }

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        String stream = message.getStream();
        RecordId id = message.getId();
        Map<String, String> map = message.getValue();
        log.info("[自动] group:[group-b] 接收到一个消息 stream:[{}],id:[{}],value:[{}]", stream, id, map);
        /*if(!StringUtils.equals(consumeType, "消费组消费B")){
            redisUtil.ack(stream, group, id.getValue());
        }*/
        consumeListener.redisUtil.del(stream, id.getValue());
    }
}

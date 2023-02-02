package com.springboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author ：zz
 * @date ：Created in 2022/5/11 16:00
 * @description：redis工具类
 */
@Component
public class RedisUtil{

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * create by: zz
     * description: 创建消费组
     * create time: 2022/5/11 16:45
     * @param:
     * @return java.lang.String
     */
    public String createGroup(String key, String group){
        return redisTemplate.opsForStream().createGroup(key, group);
    }

    /**
     * create by: zz
     * description: 获取消费者信息
     * create time: 2022/5/11 16:48
     * @param: key
     * @param: group
     * @return org.springframework.data.redis.connection.stream.StreamInfo.XInfoConsumers
     */
    public StreamInfo.XInfoConsumers queryConsumers(String key, String group){
        return redisTemplate.opsForStream().consumers(key, group);
    }

    /**
     * create by: zz
     * description: 添加Map消息
     * create time: 2022/5/11 16:28
     * @param: key
     * @param: value
     * @return
     */
    public String addMap(String key, Map<String, String> value){
        return redisTemplate.opsForStream().add(key, value).getValue();
    }

    /**
     * create by: zz
     * description: 添加Record消息
     * create time: 2022/5/11 16:30
     * @param: record
     * @return
     */
    public String addRecord(Record<String, Object> record){
        return redisTemplate.opsForStream().add(record).getValue();
    }

    /**
     * create by: zz
     * description: 读取消息
     * create time: 2022/5/11 16:52
     * @param: key
     * @return java.util.List<org.springframework.data.redis.connection.stream.MapRecord<java.lang.String,java.lang.Object,java.lang.Object>>
     */
    public List<MapRecord<String, Object, Object>> read(String key){
        return redisTemplate.opsForStream().read(StreamOffset.fromStart(key));
    }

    /**
     * create by: zz
     * description: 确认消费
     * create time: 2022/5/19 11:21
     * @param: key
     * @param: group
     * @param: recordIds
     * @return java.lang.Long
     */
    public Long ack(String key, String group, String... recordIds){
        return redisTemplate.opsForStream().acknowledge(key, group, recordIds);
    }

    /**
     * create by: zz
     * description: 确认消费
     * create time: 2022/5/25 15:07
     * @param: group
     * @param: record
     * @return java.lang.Long
     */
    public Long ack(String group, Record<String, Object> record){
        return redisTemplate.opsForStream().acknowledge(group, record);
    }

    /**
     * create by: zz
     * description: 删除消息。当一个节点的所有消息都被删除，那么该节点会自动销毁
     * create time: 2022/7/18 15:33
     * @param: key
     * @param: recordIds
     * @return java.lang.Long
     */
    public Long del(String key, String... recordIds){
        return redisTemplate.opsForStream().delete(key, recordIds);
    }

}

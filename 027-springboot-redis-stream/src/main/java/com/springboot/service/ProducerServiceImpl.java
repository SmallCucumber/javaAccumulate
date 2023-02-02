package com.springboot.service;

import com.springboot.constant.CacheConstant;
import com.springboot.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：zz
 * @date ：Created in 2022/5/19 9:29
 * @description：生产者
 */
@Slf4j
@Service
public class ProducerServiceImpl {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * create by: zz
     * description: 每5秒生产10条数据，放入stream中
     * create time: 2022/5/19 9:39
     * @param: redisKey
     * @return void
     */
    @Scheduled(cron = "5 * * * * ?")
    public void sendRecord() {
        String redisKey = CacheConstant.REDIS_STEAM_01;
        for(int i = 0; i< 10; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("a", "aa1".concat(String.valueOf(i)));
            map.put("b", "bb1".concat(String.valueOf(i)));
            map.put("c", "cc1".concat(String.valueOf(i)));
            map.put("time", String.valueOf(System.currentTimeMillis()));
            String result = redisUtil.addMap(redisKey, map);
            log.info("返回结果：{}", result);
        }
    }

}

package com.zmm.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-13 00:13
 **/

@RestController
@Slf4j
public class DeptConsumerController {

    @Resource
    private RestTemplate restTemplate;

    /**
     * 服务提供者的服务名
     */
    @Value("${com.zmm.service-url.nacos-user-com.zmm.service}")
    private String serverURL;

    @GetMapping("/consumer/dept/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id) {
        return restTemplate.getForObject(serverURL + "/dept/nacos/" + id, String.class);
    }
}

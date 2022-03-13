package com.zmm.springcloud.controller;

import com.zmm.springcloud.entity.Dept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-10 14:27
 **/
@Slf4j
@RestController
@RequestMapping("consumer")
public class DeptControllerConsumer {

    //private static final String REST_URL_PROVIDER_PREFIX = "http://localhost:8001/"; 这种方式是直调用服务方的方法，根本没有用到 Spring Cloud
    /**
     *  面向微服务编程，即通过微服务的名称来获取调用地址
     *  使用注册到 Spring Cloud Eureka 服务注册中心中的服务，即 application.name
     */
    private static final String REST_URL_PROVIDER_PREFIX = "http://gateway-service-provider";

    /**
     * //RestTemplate 是一种简单便捷的访问 restful 服务模板类，是 Spring 提供的用于访问 Rest 服务的客户端模板工具集，提供了多种便捷访问远程 HTTP 服务的方法
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取指定部门信息
     */
    @RequestMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(REST_URL_PROVIDER_PREFIX + "/dept/get/" + id, Dept.class);
    }

    /**
     * 获取部门列表
     * @return
     */
    @RequestMapping(value = "/dept/list")
    public List<Dept> list() {
        return restTemplate.getForObject(REST_URL_PROVIDER_PREFIX + "/dept/list", List.class);
    }


    @RequestMapping(value = "/dept/feign/timeout")
    public String timeout() {

        String string="异常了兄弟";
        long start=System.currentTimeMillis();
        log.info("timeout开始");
        try {
            string=restTemplate.getForObject(REST_URL_PROVIDER_PREFIX + "/dept/feign/timeout", String.class);
        } catch (Exception e) {
            log.error("timeout",e);
        }
        long end=System.currentTimeMillis();
        log.info("timeout结束={}",(end-start));
        return string;
    }

}

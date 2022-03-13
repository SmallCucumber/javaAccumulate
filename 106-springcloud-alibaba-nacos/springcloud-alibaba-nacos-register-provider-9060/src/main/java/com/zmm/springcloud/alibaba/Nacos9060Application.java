package com.zmm.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-12 23:58
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class Nacos9060Application {

    public static void main(String[] args) {
        SpringApplication.run(Nacos9060Application.class,args);
    }
}

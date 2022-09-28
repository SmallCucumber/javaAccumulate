package com.zmm.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-12 23:58
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FacadeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacadeApplication.class,args);
    }
}

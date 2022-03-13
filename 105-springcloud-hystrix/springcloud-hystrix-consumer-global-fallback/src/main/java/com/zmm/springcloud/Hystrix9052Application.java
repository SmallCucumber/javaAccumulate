package com.zmm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-12 21:46
 **/
@SpringBootApplication
@EnableEurekaClient //开启 Eureka 客户端功能
@EnableHystrix
@EnableFeignClients
public class Hystrix9052Application {

    public static void main(String[] args) {
        SpringApplication.run(Hystrix9052Application.class,args);
    }
}

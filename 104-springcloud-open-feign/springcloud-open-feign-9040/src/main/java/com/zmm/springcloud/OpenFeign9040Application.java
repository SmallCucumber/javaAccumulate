package com.zmm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //开启 OpenFeign 功能
public class OpenFeign9040Application {

    public static void main(String[] args) {
        SpringApplication.run(OpenFeign9040Application.class,args);
    }
}

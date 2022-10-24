package com.zmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients
@RefreshScope
public class Seata152Application8502 {

    public static void main(String[] args) {
        SpringApplication.run(Seata152Application8502.class,args);
    }
}

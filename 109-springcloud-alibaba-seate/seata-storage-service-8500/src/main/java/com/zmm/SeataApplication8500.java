package com.zmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients
@RefreshScope
public class SeataApplication8500 {

    public static void main(String[] args) {
        SpringApplication.run(SeataApplication8500.class,args);
    }
}

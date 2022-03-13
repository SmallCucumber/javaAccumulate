package com.zmm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClient9005Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient9005Application.class,args);
    }
}

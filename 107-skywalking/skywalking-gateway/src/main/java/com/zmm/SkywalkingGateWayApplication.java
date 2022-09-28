package com.zmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SkywalkingGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkywalkingGateWayApplication.class,args);
    }

}

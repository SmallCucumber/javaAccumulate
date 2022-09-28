package com.zmm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GateWay9023Application {

    public static void main(String[] args) {
        SpringApplication.run(GateWay9023Application.class,args);
    }

}

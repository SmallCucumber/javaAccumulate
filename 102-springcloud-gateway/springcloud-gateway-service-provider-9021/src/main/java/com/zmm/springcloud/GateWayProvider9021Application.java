package com.zmm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-08 22:20
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class GateWayProvider9021Application {

    public static void main(String[] args) {
        SpringApplication.run(GateWayProvider9021Application.class,args);
    }
}

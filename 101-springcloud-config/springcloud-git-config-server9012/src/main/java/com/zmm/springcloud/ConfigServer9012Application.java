package com.zmm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-08 22:20
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServer9012Application {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServer9012Application.class,args);
    }
}

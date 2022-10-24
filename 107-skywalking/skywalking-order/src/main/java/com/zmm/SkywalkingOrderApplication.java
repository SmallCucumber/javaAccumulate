package com.zmm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-12 23:58
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.zmm.com.zmm.mapper")
public class SkywalkingOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkywalkingOrderApplication.class,args);
    }
}

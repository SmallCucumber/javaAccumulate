package com.zmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProviderMysql8402 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderMysql8402.class,args);
    }
}

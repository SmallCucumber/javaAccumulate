package com.zmm.springboot;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 启动类上需配置将Druid自动配置屏蔽
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan(basePackages = {"com.zmm.springboot.mapper"})
public class MultiDataStuApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDataStuApplication.class, args);
    }
}



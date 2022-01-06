package com.zmm.springboot;

import com.zmm.springboot.bean.ConfigBean;
import com.zmm.springboot.bean.TestConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties({ConfigBean.class, TestConfigBean.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}

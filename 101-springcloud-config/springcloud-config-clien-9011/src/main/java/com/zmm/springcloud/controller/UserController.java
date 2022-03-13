package com.zmm.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//为了让动态（手动）的获取最新的git 配置，在添加 actuator 监控加载 RefreshScope，
@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger= LoggerFactory.getLogger(UserController.class);

    @Value("${cloud.config.version}")
    private String springCloudConfigVersion;

    @Value("${myname}")
    private String myname;

    @RequestMapping("/save")
    public String save(){
        logger.info("UserController.save");
        return "save success";
    }

    @RequestMapping("/version")
    public String version(){
        logger.info("UserController.version");

        return springCloudConfigVersion;
    }

    @RequestMapping("/myname")
    public String myname(){
        logger.info("UserController.version");

        return myname;
    }
}

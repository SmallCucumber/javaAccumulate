package com.zmm.springcloud.controller;

import com.zmm.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-12 21:54
 **/

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${server.port}")
    private String serverPort;


    @RequestMapping(value = "/user/hystrix/ok/{id}")
    public String deptInfo_Ok(@PathVariable("id") Integer id) {
        long start=System.currentTimeMillis();
        log.info("userdeptInfo_Ok开始");
        String result = userService.deptInfo_Ok(id);
        log.info("端口号：" + serverPort + " result:" + result);
        long end=System.currentTimeMillis();
        log.info("userdeptInfo_Ok结束={}",(end-start));
        return result + "，   端口号：" + serverPort;
    }

    /**
     * Hystrix 服务超时降级
      */
    @RequestMapping(value = "/user/hystrix/timeout/{id}")
    public String deptInfo_Timeout(@PathVariable("id") Integer id) {
        long start=System.currentTimeMillis();
        log.info("userdeptInfo_Timeout开始");
        String result = userService.deptInfo_Timeout(id);
        log.info("端口号：" + serverPort + " result:" + result);
        long end=System.currentTimeMillis();
        log.info("userdeptInfo_Timeout结束={}",(end-start));
        return result + "，   端口号：" + serverPort;
    }

}

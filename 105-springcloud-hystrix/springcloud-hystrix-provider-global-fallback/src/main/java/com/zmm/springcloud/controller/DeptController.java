package com.zmm.springcloud.controller;

import com.zmm.springcloud.service.DeptService;
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
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Value("${server.port}")
    private String serverPort;


    @RequestMapping(value = "/dept/hystrix/ok/{id}")
    public String deptInfo_Ok(@PathVariable("id") Integer id) {
        long start=System.currentTimeMillis();
        log.info("deptInfo_Ok开始");
        String result = deptService.deptInfo_Ok(id);
        log.info("端口号：" + serverPort + " result:" + result);
        long end=System.currentTimeMillis();
        log.info("deptInfo_Ok结束={}",(end-start));
        return result + "，   端口号：" + serverPort;
    }

    /**
     * Hystrix 服务超时降级
      */
    @RequestMapping(value = "/dept/hystrix/timeout/{id}")
    public String deptInfo_Timeout(@PathVariable("id") Integer id) {
        long start=System.currentTimeMillis();
        log.info("deptInfo_Timeout开始");
        String result = deptService.deptInfo_Timeout(id);
        log.info("端口号：" + serverPort + " result:" + result);
        long end=System.currentTimeMillis();
        log.info("deptInfo_Timeout结束={}",(end-start));
        return result + "，   端口号：" + serverPort;
    }

}

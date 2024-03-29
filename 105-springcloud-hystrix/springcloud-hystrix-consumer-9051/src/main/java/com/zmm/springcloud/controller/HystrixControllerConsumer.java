package com.zmm.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zmm.springcloud.service.DeptHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-12 22:11
 **/
@Slf4j
@RestController
public class HystrixControllerConsumer {

    @Resource
    private DeptHystrixService deptHystrixService;
    @RequestMapping(value = "/consumer/dept/hystrix/ok/{id}")
    public String deptInfo_Ok(@PathVariable("id") Integer id) {
        return deptHystrixService.deptInfo_Ok(id);
    }
    /**
     * 在客户端进行降级
     */

    @RequestMapping(value = "/consumer/dept/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "dept_TimeoutHandler") //为该请求指定专属的回退方法
    public String deptInfo_Timeout(@PathVariable("id") Integer id) {
        String s = deptHystrixService.deptInfo_Timeout(id);
        log.info(s);
        return s;
    }

    /**
     * deptInfo_Timeout方法的 专用 fallback 方法
      */
    public String dept_TimeoutHandler(@PathVariable("id") Integer id) {
        log.info("deptInfo_Timeout 出错，服务已被降级！");
        return "服务端系统繁忙，请稍后再试！（客户端 deptInfo_Timeout 专属的回退方法触发）";
    }
}

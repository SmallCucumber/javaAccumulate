package com.zmm.springcloud.controller;

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
/**
 * 全局的服务降级方法
 */
//@DefaultProperties(defaultFallback = "user_Global_FallbackMethod")
public class UserController {

    @Resource
    private DeptHystrixService deptHystrixService;

    @RequestMapping(value = "/consumer/user/hystrix/ok/{id}")
    public String deptInfo_Ok(@PathVariable("id") Integer id) {
        return deptHystrixService.userdeptInfo_Ok(id);
    }
    /**
     * 在客户端进行降级
     */

    @RequestMapping(value = "/consumer/user/hystrix/timeout/{id}")
    //@HystrixCommand
    public String deptInfo_Timeout(@PathVariable("id") Integer id) {
        String s = deptHystrixService.userdeptInfo_Timeout(id);
        log.info(s);
        return s;
    }

    /**
     * 全局的 fallback 方法，
     * 回退方法必须和 hystrix 的执行方法在相同类中
     * @DefaultProperties(defaultFallback = "user_Global_FallbackMethod") 类上注解，请求方法上使用 @HystrixCommand 注解
     */
    /*
    public String user_Global_FallbackMethod() {
        return "user_Global_FallbackMethod--C语言中文网提醒您，运行出错或服务端系统繁忙，请稍后再试！（客户端全局回退方法触发,）";
    }*/
}

package com.zmm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-13 00:03
 **/

@RestController
@Slf4j
public class DeptController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/dept/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        log.info("DeptController>>getPayment>>id={}",id);

        if(id==2){
            throw new NullPointerException();
        }else{
            return "<h2>c语言中文网提醒您，服务访问成功！</h2>服务名：spring-cloud-alibaba-provider<br /> 端口号： " + serverPort + "<br /> 传入的参数：" + id;
        }
    }

}

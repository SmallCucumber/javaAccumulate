package com.zmm.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "skywalking-order")
public interface DeptFegin {

    @GetMapping(value = "/dept/nacos/{id}")
    String getPayment(@PathVariable("id") Integer id);
}

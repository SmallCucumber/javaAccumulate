package com.zmm.springcloud.service;

import com.zmm.springcloud.service.impl.DeptHystrixFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "hystrix-provider-global-name",fallback = DeptHystrixFallBackService.class)
public interface DeptHystrixService {

    @RequestMapping(value = "/dept/hystrix/ok/{id}")
    public String deptInfo_Ok(@PathVariable("id") Integer id);

    @RequestMapping(value = "/dept/hystrix/timeout/{id}")
    public String deptInfo_Timeout(@PathVariable("id") Integer id);

    @RequestMapping(value = "/user/hystrix/ok/{id}")
    public String userdeptInfo_Ok(@PathVariable("id") Integer id);

    @RequestMapping(value = "/user/hystrix/timeout/{id}")
    public String userdeptInfo_Timeout(@PathVariable("id") Integer id);
}

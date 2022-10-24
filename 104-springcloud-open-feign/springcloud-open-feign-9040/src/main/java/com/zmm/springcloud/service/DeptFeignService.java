package com.zmm.springcloud.service;

import com.zmm.springcloud.entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 *
 */
@Component
@FeignClient(name = "gateway-com.zmm.service-provider")
public interface DeptFeignService {
    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") int id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list();


    @RequestMapping(value = "/dept/feign/timeout", method = RequestMethod.GET)
    String timeout();
}

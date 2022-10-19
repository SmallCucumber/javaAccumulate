package com.zmm.remote;

import com.zmm.entity.CommonResult;
import com.zmm.entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
//, fallback = DeptFallbackService.class
@Component
@FeignClient(value = "spring-cloud-alibaba-provider-mysql", fallback = DeptFallbackService.class)
public interface DeptFeignService {

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public CommonResult<Dept> get(@PathVariable("id") int id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public CommonResult<List<Dept>> list();

    @RequestMapping(value = "/dept/timeOut", method = RequestMethod.GET)
    CommonResult<String> timeOut();
}

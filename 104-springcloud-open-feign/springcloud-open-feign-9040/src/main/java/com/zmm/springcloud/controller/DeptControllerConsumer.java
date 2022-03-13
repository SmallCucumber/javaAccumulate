package com.zmm.springcloud.controller;

import com.zmm.springcloud.entity.Dept;
import com.zmm.springcloud.service.DeptFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-10 14:27
 **/
@Slf4j
@RestController
@RequestMapping("consumer")
public class DeptControllerConsumer {

    @Autowired
    private DeptFeignService deptFeignService;

    /**
     * 获取指定部门信息
     */
    @RequestMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable("id") Integer id) {
        return deptFeignService.get(id);
    }

    /**
     * 获取部门列表
     * @return
     */
    @RequestMapping(value = "/dept/list")
    public List<Dept> list() {
        return deptFeignService.list();
    }


    @RequestMapping(value = "/dept/feign/timeout")
    public String timeout() {
        String string="异常了兄弟";
        long start=System.currentTimeMillis();
        log.info("timeout开始");
        try {
            string=deptFeignService.timeout();
        } catch (Exception e) {
            log.error("timeout",e);
        }
        long end=System.currentTimeMillis();
        log.info("timeout结束={}",(end-start));
        return string;
    }
}

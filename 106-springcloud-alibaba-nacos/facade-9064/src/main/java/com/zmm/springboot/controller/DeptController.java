package com.zmm.springboot.controller;

import com.zmm.springboot.service.DeptFegin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    //private Logger log= LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptFegin deptFegin;

    @GetMapping(value = "/dept/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        log.info("DeptController>>getPayment>>id={}",id);
        return deptFegin.getPayment(id);
    }

}

package com.zmm.controller;

import com.zmm.service.DeptFegin;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
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

    @Autowired
    private DeptFegin deptFegin;

    @Trace(operationName = "customTraceFunction")
    @Tags({@Tag(key = "plaintext", value = "arg[0]"), @Tag(key = "ciphertext", value = "returnedObj")})
    @GetMapping(value = "/dept/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        log.info("DeptController>>getPayment>>id={}",id);
        return deptFegin.getPayment(id);
    }

}

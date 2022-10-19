package com.zmm.controller;

import com.zmm.entity.CommonResult;
import com.zmm.entity.Dept;
import com.zmm.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/dept/get/{id}")
    public CommonResult<Dept> get(@PathVariable("id") int id) {
        log.info("端口：" + serverPort + "dept/get/");

        /*try {
            TimeUnit.SECONDS.sleep(1);
            log.info("休眠 1秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Dept dept = deptService.get(id);
        if(dept==null){
            throw new NullPointerException("NullPointerException，该ID没有对应记录,空指针异常");
        }
        CommonResult<Dept> result = new CommonResult(200, "from mysql,serverPort:  " + serverPort, dept);

        return result;
    }
    @GetMapping(value = "/dept/list")
    public CommonResult<List<Dept>> list() {
        log.info("端口：" + serverPort + " dept/list/");
        List<Dept> depts = deptService.selectAll();
        CommonResult<List<Dept>> result = new CommonResult(200, "from mysql,serverPort:  " + serverPort, depts);
        return result;
    }

    @GetMapping(value = "timeOut")
    public CommonResult<String> timeOut() {
        log.info("端口：" + serverPort + " dept/timeOut/");

        /*try {
            TimeUnit.SECONDS.sleep(1);
            log.info("休眠 1秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        CommonResult<String> result = new CommonResult(200, "success","from mysql,serverPort:  " + serverPort);
        return result;
    }
}

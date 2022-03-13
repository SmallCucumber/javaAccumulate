package com.zmm.springcloud.controller;

import com.zmm.springcloud.entity.Dept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-09 22:47
 **/

@RestController
@RequestMapping("dept")
@Slf4j
public class DeptController {

    @Value("${custom.name}")
    private String customName;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/get/{id}")
    public Dept getDept(@PathVariable(name = "id",required = true) String id){
        Dept dept1=Dept.builder().deptCount(20).deptName(id).customName(customName).build();

        return dept1;
    }

    @RequestMapping("list")
    public List<Dept> getList(HttpServletRequest request){
        List<Dept> deptList=new ArrayList<>();

        String myRequestParam=request.getParameter("my-request-param");
        String myRequestHeader=request.getHeader("my-request-header");

        log.info("DeptController>>>>getList>>>>myRequestParam={}>>>myRequestHeader={}",myRequestParam,myRequestHeader);

        Dept dept1=Dept.builder().deptCount(20).deptName("人事").customName(customName).build();
        Dept dept2=Dept.builder().deptCount(22).deptName("技术").customName(customName).build();

        deptList.add(dept1);
        deptList.add(dept2);

        return  deptList;
    }

    /**
     * 超时测试,该服务的响应时间为 5 秒
     */
    @RequestMapping(value = "/feign/timeout")
    public String DeptFeignTimeout() {
        //暂停 5 秒
        long start=System.currentTimeMillis();
        log.info("timeout开始");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end=System.currentTimeMillis();
        log.info("timeout结束={}",(end-start));
        return "serverPort:"+serverPort;
    }
}

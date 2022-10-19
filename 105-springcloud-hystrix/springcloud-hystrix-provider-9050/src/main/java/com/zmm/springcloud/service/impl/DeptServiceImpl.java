package com.zmm.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zmm.springcloud.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-12 21:50
 **/
@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Override
    public String deptInfo_Ok(Integer id) {

        return "线程池：" + Thread.currentThread().getName() + "  deptInfo_Ok,id:   " + id;
    }

    /**
     * 规定 5 秒钟以内就不报错，正常运行，超过 5 秒就报错，调用指定的方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "dept_TimeoutHandler",
            commandProperties ={
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            })
    @Override
    public String deptInfo_Timeout(Integer id) {
        int outTime = 3;
        try {
            TimeUnit.SECONDS.sleep(outTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "  deptInfo_Timeout,id:   " + id + "  耗时: " + outTime;
    }

    /**
     * 当服务出现故障后，调用该方法给出友好提示
      */
    public String dept_TimeoutHandler(Integer id) {
        return  "系统繁忙请稍后再试！"+"线程池：" + Thread.currentThread().getName() + "  deptInfo_Timeout,id:   " + id;
    }
}

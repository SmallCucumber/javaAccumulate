package com.zmm.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zmm.springcloud.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-12 23:21
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
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
            })
    @Override
    public String deptInfo_Timeout(Integer id) {
        int outTime = 6;
        try {
            TimeUnit.SECONDS.sleep(outTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "  deptInfo_Timeout,id:   " + id + "  耗时: " + outTime;
    }


    @HystrixCommand(fallbackMethod = "deptCircuitBreaker_fallback", commandProperties = {
            //以下参数在 HystrixCommandProperties 类中有默认配置
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启熔断器
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "10000"), //统计时间窗
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //统计时间窗内请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //在统计时间窗口期以内，请求失败率达到 60% 时进入熔断状态
    })
    @Override
    public String deptCircuitBreaker(Integer id) {
        if (id < 0) {
            //当传入的 id 为负数时，抛出异常，调用降级方法
            throw new RuntimeException("c语言中文网提醒您，id 不能是负数！");
        }
        String serialNum = UUID.randomUUID().toString();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号为：" + serialNum;
    }


    /**
     * 当服务出现故障后，调用该方法给出友好提示
     */
    public String dept_TimeoutHandler(Integer id) {
        return  "C语言中文网提醒您，系统繁忙请稍后再试！"+"线程池：" + Thread.currentThread().getName() + "  deptInfo_Timeout,id:   " + id;
    }

    /**
     * deptCircuitBreaker 的降级方法
     */
    public String deptCircuitBreaker_fallback(Integer id) {
        return "c语言中文网提醒您，id 不能是负数,请稍后重试!\t id:" + id;
    }
}

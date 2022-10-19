package com.zmm.controller;

import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreaker;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.EventObserverRegistry;
import com.alibaba.csp.sentinel.util.TimeUtil;
import com.zmm.entity.CommonResult;
import com.zmm.entity.Dept;
import com.zmm.remote.DeptFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class DeptFeignController {

    @Value("${mysql.version}")
    private String mysqlVersion;

    @Resource
    DeptFeignService deptFeignService;

    @RequestMapping(value = "consumer/feign/dept/timeOut", method = RequestMethod.GET)
    //@SentinelResource(value = "fallback", fallback = "handlerFallback")
    public CommonResult<String> timeOut() {
        //monitor();
        System.out.println("--------->>>>主业务逻辑"+mysqlVersion);
        CommonResult<String> result = deptFeignService.timeOut();

        return result;
    }

    @RequestMapping(value = "consumer/feign/dept/get/{id}", method = RequestMethod.GET)
    //@SentinelResource(value = "fallback", fallback = "handlerFallback")
    public CommonResult<Dept> get(@PathVariable("id") int id) {
        //monitor();
        System.out.println("--------->>>>主业务逻辑"+mysqlVersion);
        CommonResult<Dept> result = deptFeignService.get(id);
        if (id == 6) {
            System.err.println("--------->>>>主业务逻辑，抛出非法参数异常");
            throw new IllegalArgumentException("IllegalArgumentException，非法参数异常....");
            //如果查到的记录也是 null 也控制正异常
        } else if (result.getData() == null) {
            System.err.println("--------->>>>主业务逻辑，抛出空指针异常");
            throw new NullPointerException("NullPointerException，该ID没有对应记录,空指针异常");
        }
        return result;
    }
    @RequestMapping(value = "consumer/feign/dept/list", method = RequestMethod.GET)
    public CommonResult<List<Dept>> list() {
        return deptFeignService.list();
    }
    //处理异常的回退方法（服务降级）
    public CommonResult handlerFallback(@PathVariable int id, Throwable e) {
        System.err.println("--------->>>>服务降级逻辑");
        Dept dept = new Dept(id, "null", "null");
        return new CommonResult(444, "SentinelFlowLimitController，服务被降级！异常信息为：" + e.getMessage(), dept);
    }



    /**
     * 自定义事件监听器，监听熔断器状态转换
     */
    public void monitor() {
        EventObserverRegistry.getInstance().addStateChangeObserver("logging",
                (prevState, newState, rule, snapshotValue) -> {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (newState == CircuitBreaker.State.OPEN) {
                        // 变换至 OPEN state 时会携带触发时的值
                        System.err.println(String.format("%s -> OPEN at %s, 发送请求次数=%.2f", prevState.name(),
                                format.format(new Date(TimeUtil.currentTimeMillis())), snapshotValue));
                    } else {
                        System.err.println(String.format("%s -> %s at %s", prevState.name(), newState.name(),
                                format.format(new Date(TimeUtil.currentTimeMillis()))));
                    }
                });
    }
}

package com.zmm.springcloud.service.impl;

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

}

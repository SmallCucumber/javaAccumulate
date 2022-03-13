package com.zmm.springcloud.service.impl;

import com.zmm.springcloud.service.DeptHystrixService;
import org.springframework.stereotype.Component;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-12 23:05
 **/
@Component
public class DeptHystrixFallBackService implements DeptHystrixService {
    @Override
    public String deptInfo_Ok(Integer id) {
        return "----------deptInfo_Ok----------C语言中文网提醒您，系统繁忙，请稍后重试！（解耦回退方法触发）-----------------------";
    }
    @Override
    public String deptInfo_Timeout(Integer id) {
        return "---------deptInfo_Timeout-----------C语言中文网提醒您，系统繁忙，请稍后重试！（解耦回退方法触发）-----------------------";
    }

    @Override
    public String userdeptInfo_Ok(Integer id) {
        return "---------userdeptInfo_Ok-----------C语言中文网提醒您，系统繁忙，请稍后重试！（解耦回退方法触发）-----------------------";
    }

    @Override
    public String userdeptInfo_Timeout(Integer id) {
        return "----------userdeptInfo_Timeout----------C语言中文网提醒您，系统繁忙，请稍后重试！（解耦回退方法触发）-----------------------";
    }
}
package com.zmm.springcloud.service;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-12 21:48
 **/
public interface UserService {

    /**
     *  hystrix 熔断器示例 ok
     */

    public String deptInfo_Ok(Integer id);

    /**
     *  hystrix 熔断器超时案例
     * @param id
     * @return
     */
    public String deptInfo_Timeout(Integer id);

}

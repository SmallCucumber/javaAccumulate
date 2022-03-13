package com.zmm.springcloud.service;

public interface DeptService {

    /**
     * hystrix 熔断器示例 ok
     * @param id
     * @return
     */
    public String deptInfo_Ok(Integer id);

    /**
     * hystrix 熔断器超时案例
     * @param id
     * @return
     */
    public String deptInfo_Timeout(Integer id);

    /**
     * Hystrix 熔断机制案例
     * @param id
     * @return
     */
    public String deptCircuitBreaker(Integer id);

}

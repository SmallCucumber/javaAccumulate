package com.zmm.service;

import com.zmm.entity.CommonResult;
import com.zmm.entity.Order;

public interface OrderService {

    /**
     * 创建订单数据
     * @param order
     */
    CommonResult create(Order order);
}

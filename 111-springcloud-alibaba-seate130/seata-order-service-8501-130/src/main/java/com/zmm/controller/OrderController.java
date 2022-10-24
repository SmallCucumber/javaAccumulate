package com.zmm.controller;

import com.zmm.entity.CommonResult;
import com.zmm.entity.Order;
import com.zmm.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;
    @GetMapping("/order/create/{productId}/{count}/{money}")
    public CommonResult create(@PathVariable("productId") Integer productId, @PathVariable("count") Integer count
            , @PathVariable("money") BigDecimal money) {
        Order order = new Order();
        order.setProductId(Integer.valueOf(productId).longValue());
        order.setCount(count);
        order.setMoney(money);
        return orderService.create(order);
    }
}

package com.zmm.controller;

import com.zmm.entity.CommonResult;
import com.zmm.service.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@Slf4j
public class AccountController {

    @Resource
    AccountServiceImpl accountService;

    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){

        int decrease=accountService.decrease(userId,money);

        return new CommonResult(200, "扣款成功",decrease);
    }
}

package com.zmm.springcloud.controller;

import com.zmm.springcloud.config.IgnoreWhiteProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IgnoreWhiteProperties ignoreWhite;

    @RequestMapping("/ignoreWhite")
    public String[] ignoreWhite(){

        return ignoreWhite.getWhites();
    }
}

package com.zmm.springboot.controller;

import com.zmm.springboot.bean.BlogProperties;
import com.zmm.springboot.bean.ConfigBean;
import com.zmm.springboot.bean.TestConfigBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private BlogProperties blogProperties;

    @Autowired
    private ConfigBean configBean;

    @Autowired
    private TestConfigBean testConfigBean;

    @RequestMapping()
    private String index() {
        return testConfigBean.getName()+"，"+testConfigBean.getAge();
    }
}


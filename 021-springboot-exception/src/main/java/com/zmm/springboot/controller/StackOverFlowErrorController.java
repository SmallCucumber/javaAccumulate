package com.zmm.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

public class StackOverFlowErrorController {

    private static final Logger logger = LoggerFactory.getLogger(StackOverFlowErrorController.class);

    /**
     * 递归调用一个方法，使其超过栈的最大深度
     */
    @RequestMapping("/stackOverFlowError")
    public void stackOverFlowError(){
        Byte[] b=new Byte[512];
        Integer i=0;
        logger.info("stackOverFlowError>>>"+i++);
        stackOverFlowError();
    }
}

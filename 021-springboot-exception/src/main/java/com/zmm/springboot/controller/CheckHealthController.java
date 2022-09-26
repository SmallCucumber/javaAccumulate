package com.zmm.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CheckHealthController {

    private static final Logger logger = LoggerFactory.getLogger(CheckHealthController.class);

    @RequestMapping("/checkHealth")
    public String stackOverFlowError(){
        logger.info("调用服务监测接口-----------------------{}",new Date());

        MemoryMXBean mxb = ManagementFactory.getMemoryMXBean();
        //Heap
        logger.info("Max:" + mxb.getHeapMemoryUsage().getMax() / 1024 / 1024 + "MB");    //Max:1776MB
        logger.info("Init:" + mxb.getHeapMemoryUsage().getInit() / 1024 / 1024 + "MB");  //Init:126MB
        logger.info("Committed:" + mxb.getHeapMemoryUsage().getCommitted() / 1024 / 1024 + "MB");   //Committed:121MB
        logger.info("Used:" + mxb.getHeapMemoryUsage().getUsed() / 1024 / 1024 + "MB");  //Used:7MB
        logger.info(mxb.getHeapMemoryUsage().toString());    //init = 132120576(129024K) used = 8076528(7887K) committed = 126877696(123904K) max = 1862270976(1818624K)

        //Non heap
        logger.info("Max:" + mxb.getNonHeapMemoryUsage().getMax() / 1024 / 1024 + "MB");    //Max:0MB
        logger.info("Init:" + mxb.getNonHeapMemoryUsage().getInit() / 1024 / 1024 + "MB");  //Init:2MB
        logger.info("Committed:" + mxb.getNonHeapMemoryUsage().getCommitted() / 1024 / 1024 + "MB");   //Committed:8MB
        logger.info("Used:" + mxb.getNonHeapMemoryUsage().getUsed() / 1024 / 1024 + "MB");  //Used:7MB
        logger.info(mxb.getNonHeapMemoryUsage().toString());    //init = 2555904(2496K) used = 7802056(7619K) committed = 9109504(8896K) max = -1(-1K)

        return "服务监测接口返回"+new Date();
    }

    @RequestMapping("/checkList")
    public List<String> checkList(){
        logger.info("调用服务监测接口-----------------------{}",new Date());

        List<String> list=new ArrayList<>();
        for(int i=0;i<50;i++){

            list.add(String.valueOf(i));
        }

        return list;
    }
}

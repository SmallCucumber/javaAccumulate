package com.zmm.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Slf4j
@Service
public class AsyncService {

    @Async
    public Future<String> method1(String str) throws InterruptedException {
        log.info("start>>method1");
        Thread.sleep(1000*2);

        log.info("end>>method1");
        return new AsyncResult<>( str);
    }
    @Async
    public Future<String> method2(String str) throws InterruptedException {
        log.info("start>>method2");
        Thread.sleep(1000*3);
        log.info("end>>method2");
        return new AsyncResult<>(str);
    }
    @Async
    public Future<String> method3(String str) throws InterruptedException {
        log.info("start>>method3");
        Thread.sleep(1000*5);
        log.info("end>>method3");
        return new AsyncResult<>(str);
    }
}

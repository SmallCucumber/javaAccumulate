package com.zmm.springboot.web;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class CompletableFutureTest2 {

    public static void thenApplyAsyncs() throws InterruptedException, ExecutionException, TimeoutException {
        TimeInterval timer = DateUtil.timer();

        CompletableFuture<Integer> deviceFuture=CompletableFuture.supplyAsync(()->{

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "deviceFuture";
        }).thenApplyAsync(deptDatas->{

            return 1;
        });

        //Integer deviceListDto=deviceFuture.get(5,TimeUnit.SECONDS);

        //花费毫秒数
        Long deviceFutureEndTimer=timer.interval();
        log.info("deviceFutureEndTimer={}",deviceFutureEndTimer);
        //返回花费时间，并重置开始时间
        timer.intervalRestart();

        CompletableFuture<String> deviceCameraFuture=deviceFuture.thenApplyAsync(device -> {
            log.info("deviceCameraFuture>>device={}",device);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "page";
        });

        CompletableFuture<String> monitorDtoListFuture=deviceFuture.thenApplyAsync(device -> {
            log.info("deviceCameraFuture>>device={}",device);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "monitorDtoList";
        });

        String page=deviceCameraFuture.get(5, TimeUnit.SECONDS);

        //花费毫秒数
        Long deviceCameraFutureEndTimer=timer.interval();
        log.info("deviceCameraFutureEndTimer={}",deviceCameraFutureEndTimer);
        //返回花费时间，并重置开始时间
        timer.intervalRestart();

        String monitorDtoList= monitorDtoListFuture.get(5,TimeUnit.SECONDS);

        //花费毫秒数
        Long monitorDtoListFutureEndTimer=timer.interval();
        log.info("monitorDtoListFutureEndTimer={}",monitorDtoListFutureEndTimer);
        //返回花费时间，并重置开始时间
        timer.intervalRestart();
    }

    public static void supplyAsync() throws ExecutionException, InterruptedException {

        TimeInterval timer = DateUtil.timer();

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            //throw new NullPointerException();
            try {
                // 睡眠3s模拟延时
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 22;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                // 睡眠3s模拟延时
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        Integer intfuture2=future2.get();

        //花费毫秒数
        Long intfuture2EndTimer=timer.interval();
        log.info("intfuture2EndTimer={}",intfuture2EndTimer);
        //返回花费时间，并重置开始时间
        timer.intervalRestart();

        Integer intFuture=future.get();

        //花费毫秒数
        Long int1EndTimer=timer.interval();
        log.info("int1EndTimer={}",int1EndTimer);
        //返回花费时间，并重置开始时间
        timer.intervalRestart();

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        thenApplyAsyncs();
        //supplyAsync();

    }

}

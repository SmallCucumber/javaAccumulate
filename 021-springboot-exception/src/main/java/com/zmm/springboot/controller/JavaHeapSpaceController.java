package com.zmm.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
public class JavaHeapSpaceController {
    private static final Logger logger = LoggerFactory.getLogger(JavaHeapSpaceController.class);

    /**
     * 让系统内存溢出
     */
    @GetMapping("/error1")
    public void error(){

        List<Byte[]> list = new ArrayList<>();
        while (true){
            logger.info(new Date().toString() + Thread.currentThread() + "==");
            Byte[] b = new Byte[1024 * 1024 * 1];
            list.add(b);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用是循环创建对象，是堆内存溢出
     */
    @RequestMapping("/error2")
    public void javaHeapSpace(){
        String str = "hello world";
        while (true){
            str += new Random().nextInt(1111111111) + new Random().nextInt(222222222);
            /**
             *  intern()方法：
             * （1）当常量池中不存在这个字符串的引用，将这个对象的引用加入常量池，返回这个对象的引用。
             * （2）当常量池中存在这个字符串的引用，返回这个对象的引用；
             */
            str.intern();
        }
    }
}

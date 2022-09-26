package com.zmm.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnableCreateThreadController {

    /**
     * 友情提示:千万别在windows中运行这段代码，如果不小心和我一样试了，那就只能强制重启了
     */
    @RequestMapping("/unableCreateThread")
    public void unableCreateThread(){
        for (int i = 0; ; i++) {
            System.out.println("i的值为：" + i);
            new Thread(()->{
                try{
                    Thread.sleep(1000*1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

package com.zmm.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.ByteBuffer;

@RestController
public class DirectBufferMemoryController {

    @RequestMapping("/directBufferMemory")
    public void directBufferMemory(){
        System.out.println("初始配置的最大本地内存为："+ (sun.misc.VM.maxDirectMemory()/1024/1024)+"MB");
        // 在jvm参数里设置的最大内存为5M，
        ByteBuffer buffer = ByteBuffer.allocateDirect(6*1024*1024);
    }
}

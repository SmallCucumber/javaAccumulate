package com.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test1")
    public void test1() {
        while (true) {
            System.out.println("===test1==" + LocalDateTime.now());
        }
    }
}

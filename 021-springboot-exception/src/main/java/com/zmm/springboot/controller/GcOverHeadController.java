package com.zmm.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GcOverHeadController {

    @RequestMapping("/gcOverHead")
    public void gcOverHead(){
        int i = 0;
        List<String> list = new ArrayList<>();
        try{
            while(true){
                list.add(String.valueOf(++i).intern());
            }
        }catch(Throwable e){
            System.out.println("i的值为：" + i);
            e.printStackTrace();
            throw e;
        }
    }
}

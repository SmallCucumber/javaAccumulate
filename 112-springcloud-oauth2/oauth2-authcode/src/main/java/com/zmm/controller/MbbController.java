package com.zmm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mbb")
public class MbbController {

    @GetMapping("/init")
    public String init(){
        return "mbb init";
    }

}

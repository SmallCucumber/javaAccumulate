package com.zmm.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * swagger 接口
 * 
 * @author zmm
 */
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController
{
    @GetMapping()
    public String index()
    {
        return "redirect:/swagger-ui.html";
    }
}

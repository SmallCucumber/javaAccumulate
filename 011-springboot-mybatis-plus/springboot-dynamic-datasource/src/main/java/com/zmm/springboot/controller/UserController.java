package com.zmm.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zmm.springboot.entity.DataSourceDTO;
import com.zmm.springboot.mapper.DataSourceMapper;
import com.zmm.springboot.service.DataSourceService;
import com.zmm.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DataSourceService dataSourceService;

    @PostConstruct
    public void init(){
        List<DataSourceDTO> list = dataSourceService.selectList();
        for (DataSourceDTO dataSourceDTO : list) {
            dataSourceService.add(dataSourceDTO);
        }
    }

    @GetMapping("/add")
    public void add(){

        userService.add();
    }


    @GetMapping("/addHeader")
    public void addHeader(){

        userService.addHeader();
    }
}

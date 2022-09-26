package com.zmm.springboot.web;

import com.zmm.springboot.model.User;
import com.zmm.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/add")
    public void save(@RequestBody User user) throws Exception {
        userService.insert(user);
    }

}

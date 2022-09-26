package com.zmm.springboot.web;

import com.zmm.springboot.model.User;
import com.zmm.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/getUser")
    public User getUser() throws ExecutionException, InterruptedException {
        return userService.getUser();
    }

}

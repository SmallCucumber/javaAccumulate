package com.zmm.springboot.web;

import com.zmm.springboot.model.User;
import com.zmm.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/getUser")
    public User getUser() throws ExecutionException, InterruptedException {
        return userService.getUser();
    }

    @RequestMapping("/getUser1")
    public User getUser1() throws ExecutionException, InterruptedException {
        return userService.getUser1();
    }

    @RequestMapping("/getUser2")
    public String getUser2() throws ExecutionException, InterruptedException, TimeoutException {
        return userService.getUser2();
    }
}

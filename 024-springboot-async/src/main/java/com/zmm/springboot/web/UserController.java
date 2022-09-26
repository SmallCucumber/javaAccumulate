package com.zmm.springboot.web;

import com.zmm.springboot.model.User;
import com.zmm.springboot.service.AsyncService;
import com.zmm.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/getUser")
    public User getUser() throws ExecutionException, InterruptedException {
        return userService.getUser();
    }

    @RequestMapping("/asyncMethod")
    public String asyncMethod() throws InterruptedException, ExecutionException {

        Future<String> result1 = asyncService.method1("I");
        Future<String> result2 = asyncService.method2("love");
        Future<String> result3 = asyncService.method3("async");

        String result ="";

        return result;
    }
}

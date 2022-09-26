package com.zmm.springboot.web;

import com.zmm.springboot.model.Dept;
import com.zmm.springboot.model.User;
import com.zmm.springboot.service.DeptService;
import com.zmm.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private UserService userService;


    @RequestMapping("/getById/{id}")
    public Dept getById(@PathVariable("id") Integer id) throws Exception{

        CompletableFuture<Dept> completableFuture=CompletableFuture.supplyAsync(()->{

            return deptService.getById(id);
        });

        Dept dept3=completableFuture.getNow(new Dept(3, "研发三部"));

        log.info(dept3.toString());

        Dept returnDept=new Dept();
        try {
            returnDept=completableFuture.get(3, TimeUnit.SECONDS);

        }catch (Exception e){
            log.error("",e);
        }

        log.info(returnDept.toString());

        return returnDept;
    }

    @RequestMapping("/getUser/{id}")
    public User getById1(@PathVariable("id") Integer id) throws Exception{

        CompletableFuture<User> completableFuture= CompletableFuture.supplyAsync(()->{

            return deptService.getById(id);
        }).thenApplyAsync(dept -> {

            log.info(dept.toString());
            try {
                return userService.getUser();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

        User returnUser=completableFuture.get();

        return returnUser;
    }
}

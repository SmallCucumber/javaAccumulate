package com.zmm.springboot.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.zmm.springboot.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
@Service
public class UserService {

    @Autowired
    private ThreadPoolTaskExecutor myExecutor;

    public User getUser() throws ExecutionException, InterruptedException {

        TimeInterval timer = DateUtil.timer();

        List<FutureTask> futureTaskList=new ArrayList<>();

        FutureTask futureTask=new FutureTask(new Callable() {
            @Override
            public Integer call() throws Exception {
                sleep();
                return 1;
            }
        });

        futureTaskList.add(futureTask);
        myExecutor.submit(futureTask);

        FutureTask<User> futureTaskUser=new FutureTask(new Callable() {
            @Override
            public User call() throws Exception {
                return getData();
            }
        });

        futureTaskList.add(futureTaskUser);
        myExecutor.submit(futureTaskUser);

        User user=null;
        for(FutureTask futureTaskTemp:futureTaskList){

            Object futureTaskReslut=futureTaskTemp.get();

            log.info("futureTaskReslut={}",futureTaskReslut.toString());
            if(futureTaskReslut instanceof User){
                user= (User) futureTaskReslut;
            }

            //花费毫秒数
            Long taskEndTimer=timer.interval();
            log.info("taskEndTimer={}",taskEndTimer);
            //返回花费时间，并重置开始时间
            timer.intervalRestart();
        }

        return user;
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        }catch (Exception e){

        }
    }

    private User getData() {

        try {
            Thread.sleep(2000);
        }catch (Exception e){

        }
        User user=new User();
        user.setId(1L);
        user.setUserName("zhagnsan");
        user.setPassWord("123");
        user.setNickName("张三");
        return user;
    }
}

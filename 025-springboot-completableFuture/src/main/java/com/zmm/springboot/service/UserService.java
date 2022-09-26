package com.zmm.springboot.service;

import com.zmm.springboot.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class UserService {

    /**
     * maximumPoolSize必须大于等于corePoolSize不然会报错
     *
     * corePoolSize < 0 ||
     * maximumPoolSize <= 0 ||
     * maximumPoolSize < corePoolSize ||
     * keepAliveTime < 0
     *
     * 如果核心线程数被占满后任务被加入到阻塞队列这样还是会需要花费很长的时间
     */

    public User getUser() throws ExecutionException, InterruptedException {
        log.info("线程：" + Thread.currentThread().getName() + " getUser()");

        User user=getData();

        log.info(user.toString());
        return user;
    }


    private void sleep() {
        try {
            Thread.sleep(2000);
        }catch (Exception e){

        }
    }

    private User getData() {

        sleep();

        User user=new User();
        user.setId(1L);
        user.setUserName("zhagnsan");
        user.setPassWord("123");
        user.setNickName("张三");
        return user;
    }
}

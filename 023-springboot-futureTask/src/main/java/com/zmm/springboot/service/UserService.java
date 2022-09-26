package com.zmm.springboot.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.zmm.springboot.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
    private ExecutorService executor = new ThreadPoolExecutor(2,2,50,TimeUnit.SECONDS,new ArrayBlockingQueue(10));

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
        executor.submit(futureTask);

        FutureTask<User> futureTaskUser=new FutureTask(new Callable() {
            @Override
            public User call() throws Exception {
                return getData();
            }
        });

        futureTaskList.add(futureTaskUser);
        executor.submit(futureTaskUser);

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

    public User getUser1() throws ExecutionException, InterruptedException {
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
        executor.submit(futureTask);

        FutureTask<User> futureTaskUser=new FutureTask(new Callable() {
            @Override
            public User call() throws Exception {
                return getData();
            }
        });

        futureTaskList.add(futureTaskUser);
        executor.submit(futureTaskUser);

        Thread.sleep(3000);

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

    public String getUser2() throws ExecutionException, InterruptedException{
        CompletableFuture<String> monitorDtoListFuture = CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        });
        String s="";
        if(monitorDtoListFuture!=null){
            try {
                s=monitorDtoListFuture.get(8, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                log.info("12344");
                e.printStackTrace();
            }
        }

        return s;
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

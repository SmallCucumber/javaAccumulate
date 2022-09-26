package com.zmm.springboot.web;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.zmm.springboot.model.Dept;
import com.zmm.springboot.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class CompletableFutureTest{

    public static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
            log.info("supplyAsync");
            return 1;
        });

        CompletableFuture<String> future2 = CompletableFuture.completedFuture("completedFuture");
        Integer integer = future.get();
        //Integer integer = future.get(1, TimeUnit.SECONDS);
        Integer integer2 = future.getNow(2);

        String futureString = future2.get();
        log.info("supplyAsync={},futureString={}",integer,futureString);
    }

    public static void thenApply2() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future= CompletableFuture.supplyAsync( () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        });

        Integer futureInteger=future.get();
        log.info("futureInteger={}",futureInteger);

        future.thenApply( x -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return x + 1;
        })
                .thenApply( x -> x + 1 )
                .thenAccept( x -> log.info("thenApply={}",x) );
    }

    public static void thenApplyAsync2() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future= CompletableFuture.supplyAsync( () -> {

            /*try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            return 0;
        });

        Integer futureInteger=future.get();
        log.info("futureInteger={}",futureInteger);

        future.thenApplyAsync( x -> {
            log.info("thenApply");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return x + 1;
        })
                .thenApplyAsync( x -> {
                    log.info("thenApplyAsync");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return x + 1;
                } )
                .thenApplyAsync( x -> {
                    log.info("thenApplyAsync2");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return x + 1;
                } )
                .thenAccept( x -> log.info("thenApply={}",x) );
    }

    /**
     * 获取结果
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void get() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{

            return 1;
        });
        //Integer integer = future.get();
        //Integer integer = future.get(1, TimeUnit.SECONDS);
        Integer integer2 = future.getNow(2);
        log.info("integer2={}",integer2);
    }


    /**
     * 计算完成后续操作1——complete
     */
    public static void whenComplete(){
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 10086;
        });
        //同步
        future.whenComplete((result, error) -> {
            log.info("拨打"+result);
            log.error("",error);
        });
        //异步
        future.whenCompleteAsync((result, error) -> {
            log.info("拨打"+result);
            log.error("",error);
        });
    }

    /**
     * 计算完成后续操作2——handle
     *
     * handle方法是可以自定义返回类型
     */
    public static void handle(){
        // 开启一个异步方法
        CompletableFuture<List> future = CompletableFuture.supplyAsync(() -> {
            List<String> list = new ArrayList<>();
            list.add("语文");
            list.add("数学");
            // 获取得到今天的所有课程
            return list;
        });
        // 使用handle()方法接收list数据和error异常
        CompletableFuture<Integer> future2 = future.handle((list,error)-> {
            // 如果报错，就打印出异常
            log.error("",error);
            // 如果不报错，返回一个包含Integer的全新的CompletableFuture
            return list.size();
            // 注意这里的两个CompletableFuture包含的返回类型不同
        });
        //异步
        CompletableFuture<Integer> future3 = future.handleAsync((list,error)-> {
            // 如果报错，就打印出异常
            log.error("",error);
            // 如果不报错，返回一个包含Integer的全新的CompletableFuture
            return list.size();
            // 注意这里的两个CompletableFuture包含的返回类型不同
        });
    }

    /**
     * 计算完成的后续操作3——apply
     * thenApply与handle一样，只是把异常向上抛了
     */
    public static void thenApply(){
        // 开启一个异步方法
        CompletableFuture<List> future = CompletableFuture.supplyAsync(() -> {
            List<String> list = new ArrayList<>();
            list.add("语文");
            list.add("数学");
            // 获取得到今天的所有课程
            return list;
        });
        // 使用handle()方法接收list数据和error异常
        CompletableFuture<Integer> future2 = future.thenApply((list)-> {
            // 如果报错，就打印出异常
            // 如果不报错，返回一个包含Integer的全新的CompletableFuture
            return list.size();
            // 注意这里的两个CompletableFuture包含的返回类型不同
        });
        //异步
        CompletableFuture<Integer> future3 = future.thenApplyAsync((list)-> {
            // 如果报错，就打印出异常
            // 如果不报错，返回一个包含Integer的全新的CompletableFuture
            return list.size();
            // 注意这里的两个CompletableFuture包含的返回类型不同
        });
    }

    /**
     * 捕获中间产生的异常——exceptionally
     *
     * 可以帮我们捕捉到所有中间过程的异常，方法会给我们一个异常作为参数，我们可以处理这个异常，
     * 同时返回一个默认值，跟服务降级 有点像，默认值的类型和上一个操作的返回值相同。
     * 小贴士 ：向线程池提交任务的时候发生的异常属于外部异常，是无法捕捉到的，毕竟还没有开始执行任务。
     * 作者也是在触发线程池拒绝策略的时候发现的。exceptionally（） 无法捕捉RejectedExecutionException（）
     */
    public static void exceptionally(){
        // 实例化一个CompletableFuture,返回值是Integer
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 返回null
            return null;
        });

        CompletableFuture<String> exceptionally = future.thenApply(result -> {
            // 制造一个空指针异常NPE
            int i = result;
            return i;
        }).thenApply(result -> {
            // 这里不会执行，因为上面出现了异常
            String words = "现在是" + result + "点钟";
            return words;
        }).exceptionally(error -> {
            // 我们选择在这里打印出异常
            error.printStackTrace();
            // 并且当异常发生的时候，我们返回一个默认的文字
            return "出错啊~";
        });

        exceptionally.thenAccept(System.out::println);
    }

    /**
     * 组合式异步编程
     */
    public static void thenApplyAsync(){
        CompletableFuture<Dept> future = CompletableFuture.supplyAsync(() -> {
            // 根据学生姓名获取学生信息
            User user=new User();
            user.setId(1L);
            user.setUserName("zhagnsan");
            user.setPassWord("123");
            user.setNickName("张三");

            return user;
        }).thenApplyAsync(user -> {
            // 再根据学生信息获取今天的课程
            return new Dept(Integer.valueOf(user.getId().toString()),"部门9");
        });
    }

    /**
     * 组合式异步编程-thenCompose
     * 将两个异步计算合并为一个，这两个异步计算之间相互独立，互不依赖
     */
    public static void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<List<String>> total = CompletableFuture.supplyAsync(() -> {
            // 第一个任务获取美术课需要带的东西，返回一个list
            List<String> stuff = new ArrayList<>();
            stuff.add("画笔");
            stuff.add("颜料");
            return stuff;
        }).thenCompose(list -> {
            // 向第二个任务传递参数list(上一个任务美术课所需的东西list)
            CompletableFuture<List<String>> insideFuture = CompletableFuture.supplyAsync(() -> {
                List<String> stuff = new ArrayList<>();
                // 第二个任务获取劳技课所需的工具
                stuff.add("剪刀");
                stuff.add("折纸");
                // 合并两个list，获取课程所需所有工具
                List<String> allStuff = Stream.of(list, stuff).flatMap(Collection::stream).collect(Collectors.toList());
                return allStuff;
            });
            return insideFuture;
        }).exceptionally(error->{
            log.error("",error);
            return null;
        });
        log.info(total.get().toString());
        log.info(total.join().toString());
        //System.out.println(total.join().size());
    }

    /**
     * 组合式异步编程-thenCombine
     * 将两个异步计算合并为一个，这两个异步计算之间相互独立，互不依赖
     */
    public static void thenCombine(){
        CompletableFuture<List<String>> painting = CompletableFuture.supplyAsync(() -> {
            // 第一个任务获取美术课需要带的东西，返回一个list
            List<String> stuff = new ArrayList<>();
            stuff.add("画笔");
            stuff.add("颜料");
            return stuff;
        });
        CompletableFuture<List<String>> handWork = CompletableFuture.supplyAsync(() -> {
            // 第二个任务获取劳技课需要带的东西，返回一个list
            List<String> stuff = new ArrayList<>();
            stuff.add("剪刀");
            stuff.add("折纸");
            return stuff;
        });
        CompletableFuture<List<String>> total = painting
                // 传入handWork列表，然后得到两个CompletableFuture的参数Stuff1和2
                .thenCombine(handWork, (stuff1, stuff2) -> {
                    // 合并成新的list
                    List<String> totalStuff = Stream.of(stuff1, stuff1)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList());
                    return totalStuff;
                });
        System.out.println(total.join().toString());
    }

    /**
     * 获取所有完成结果——allOf
     *
     * allOf方法，当所有给定的任务完成后，返回一个全新的已完成CompletableFuture
     */
    public static void allOf(){
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                //使用sleep()模拟耗时操作
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                //使用sleep()模拟耗时操作
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        });
        CompletableFuture.allOf(future1, future1);

        TimeInterval timer = DateUtil.timer();

        Integer int2=future2.join();

        //花费毫秒数
        Long int2EndTimer=timer.interval();
        log.info("int2EndTimer={}",int2EndTimer);
        //返回花费时间，并重置开始时间
        timer.intervalRestart();

        Integer int1=future1.join();

        //花费毫秒数
        Long int1EndTimer=timer.interval();
        log.info("int1EndTimer={}",int1EndTimer);
        //返回花费时间，并重置开始时间
        timer.intervalRestart();

        // 输出3
        System.out.println(int1+int2);
    }

    /**
     * 获取率先完成的任务结果——anyOf
     *
     */
    public static void anyOf(){
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            //throw new NullPointerException();
            return 22;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                // 睡眠3s模拟延时
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        CompletableFuture<Object> anyOf = CompletableFuture
                .anyOf(future, future2)
                .exceptionally(error -> {
                    error.printStackTrace();
                    return 2;
                });
        System.out.println(anyOf.join());
    }

    /**
     * 并发任务
     */
    public static void concurrenceTask(){
        TimeInterval timer = DateUtil.timer();

        // 自定义一个线程池
        ExecutorService executorService = new ThreadPoolExecutor(2,2,50,TimeUnit.SECONDS,new ArrayBlockingQueue(2));
        //ExecutorService executorService = Executors.newFixedThreadPool(4);
        // 循环创建10个CompletableFuture
        try{
            List<CompletableFuture<Integer>> collect = IntStream.range(1, 10).mapToObj(i -> {
                CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                    // 在i=5的时候抛出一个NPE
                    if (i == 5) {
                        throw new NullPointerException();
                    }
                    try {
                        // 每个依次睡眠1-9s，模拟线程耗时
                        TimeUnit.SECONDS.sleep(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                    return i;
                }, executorService)
                        // 这里处理一下i=5时出现的NPE
                        // 如果这里不处理异常，那么异常会在所有任务完成后抛出,小伙伴可自行测试
                        .exceptionally(Error -> {
                            try {
                                TimeUnit.SECONDS.sleep(5);
                                System.out.println(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return 100;
                        });
                return future;
            }).collect(Collectors.toList());

            // List列表转成CompletableFuture的Array数组,使其可以作为allOf()的参数
            // 使用join()方法使得主线程阻塞，并等待所有并行线程完成
            /**
             * 注意：
             * 需要try catch由于exceptionally不能捕获到线程池的拒绝策略异常，如果触发了线程池的拒绝策略join会导致线程一直阻塞在这里。
             */

            CompletableFuture.allOf(collect.toArray(new CompletableFuture[]{})).join();
        }catch (Exception e){
            log.error("",e);
        }

        //花费毫秒数
        Long int1EndTimer=timer.interval();
        log.info("最终耗时 int1EndTimer={}",int1EndTimer);
        executorService.shutdown();
    }

    /**
     * thenApply2方法和thenApplyAsync2方法可以得出thenApplyAsync和thenApply一个开启子线程一个没有主线程,会按照方法流的顺序走。
     * 如果开启异步方法不阻塞的话主线程有可能在子线程方法没有执行完就先执行完了。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //thenApply2();
        //thenApplyAsync2();
        //supplyAsync();
        //thenCompose();
        //allOf();
        //anyOf();
        concurrenceTask();
        //get();

        //TimeUnit.SECONDS.sleep(5);
    }
}

package com.langchao.demothread.thread;

import java.util.concurrent.*;

/**
 * @author liyuan
 * @date 2020-06-09-23:55
 */
public class ThreadPoolExecutorTest {

    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100000), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    /**
     * Creates a new {@code ThreadPoolExecutor} with the given initial
     *
     * @param corePoolSize    核心线程池的数量,创建完成后准备就绪,等待被调用,除非线程设置了超时设置
     * @param maximumPoolSize 最大线程数量;控制资源并发
     * @param keepAliveTime   存活时间 ,当前线程数量大于最大线程数量时,释放空闲的线程.
     *                        只要线程空闲大于指定的存货时间.核心线程不会被释放
     * @param unit            时间单位
     * @param workQueue       BlockingQueue<Runnable> workQueue, 阻塞队列,如果任务有很多,
     *                        就会将目前多的任务放在队列里面,只要有线程空闲,
     *                        就会去队列里面取出新的任务继续执行
     * @param threadFactory   创建线程的工厂
     * @param handler         如果阻塞队列满了,按照我们指定的拒绝策略拒绝执行任务
     */
    public void test(int corePoolSize,
                     int maximumPoolSize,
                     long keepAliveTime,
                     TimeUnit unit,
                     BlockingQueue<Runnable> workQueue,
                     ThreadFactory threadFactory,
                     RejectedExecutionHandler handler) throws ExecutionException, InterruptedException {

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100000), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        // 所有都可以被回收的线程池
        Executors.newCachedThreadPool();

        // 10个固定大小的线程池  核心 == 最大
        Executors.newFixedThreadPool(10);

        // 定时任务线程池
        Executors.newScheduledThreadPool(10);

        // 单线程池,后台从队列获取任务,挨个执行
        Executors.newSingleThreadExecutor();


        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getId() + "线程执行----------");
        }, threadPoolExecutor);

        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + "线程执行----------");
            return 1;
        }, threadPoolExecutor).whenComplete((res, excption) -> {
            // 可以感知异常,无法修改返回结果
            System.out.println("成功:" + res + "异常是:" + excption);
        }).exceptionally(throwable -> {
            System.out.println("我这边处理异常");
            return 11111;
        });
        System.out.println(future.get());

        CompletableFuture<Integer> supplyAsync2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + "线程执行----------");
            return 1;
        }, threadPoolExecutor).handle((res, thr) -> {
            // 方法执行完成之后的处理
            if (res != null) {
                return res*2;
            }
            if(thr!=null){
                return 0;
            }
            return 0;
        });
        System.out.println(supplyAsync2.get());

        // 第二个线程无法获取上一个线程的返回值
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + "线程执行----------");
            return 1;
        }, threadPoolExecutor).thenRunAsync(() -> {
            System.out.println("串行处理没有返回值,不接受第一次的返回值,只有第一次的返回值");
        }, threadPoolExecutor);
        System.out.println(completableFuture.get());


        // 第二个线程获取上一个线程的返回值
        CompletableFuture<Void> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + "线程执行----------");
            return 1;
        }, threadPoolExecutor).thenAcceptAsync(res -> {
            System.out.println("串行处理没有返回值,不接受第一次的返回值,只有第一次的返回值");
        }, threadPoolExecutor);
        System.out.println(completableFuture2.get());


        // 第二个线程获取上一个线程的返回值
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + "线程执行----------");
            return 1;
        }, threadPoolExecutor).thenApplyAsync(res -> {
            System.out.println("串行处理没有返回值,接受第一次的返回值,有两个的返回值");
            return "第二次的返回值"+res;
        }, threadPoolExecutor);
        System.out.println(stringCompletableFuture.get());



    }


}

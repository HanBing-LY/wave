package com.liyuan.demo.threadjuc.juc.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author liyuan
 * @description 闭锁, 在完成某些运算时, 只有完成其他所有线程的运算全部完成, 当前运算才继续执行
 * @date 2020-06-14-15:10
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        LatchDemo latchDemo = new LatchDemo(countDownLatch);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            new Thread(latchDemo).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {

        }

        long end = System.currentTimeMillis();

        System.out.println("耗费时间"+(end - start));
    }

}

package com.liyuan.demo.threadjuc.juc.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author liyuan
 * @description
 * @date 2020-06-14-15:21
 */
public class LatchDemo implements Runnable {

    private final CountDownLatch countDownLatch;

    public LatchDemo(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
//            for (int i = 0; i < 50000; i++) {
//                if (i % 2 == 0) {
//                    System.out.println(i);
//                }
//            }
            System.out.println(666);
        } finally {
            synchronized (countDownLatch) {
                countDownLatch.countDown();
            }
        }
    }
}

package com.liyuan.demo.threadjuc.juc.cas;

/**
 * @author liyuan
 * @description cas 算法
 * @date 2020-06-14-14:55
 */
public class CompareAndSwapTest {

    public static void main(String[] args) {

        final CompareAndSwap compareAndSwap = new CompareAndSwap();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int expectedValue = compareAndSwap.get();
                boolean b = compareAndSwap.compareAndSet(expectedValue, (int) (Math.random() * 101));
                System.out.println(b);
            }).start();
        }
    }
}

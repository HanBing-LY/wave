package com.liyuan.wave.juc.volat;

/**
 * @author liyuan
 * @description volat :当多个线程进行操作共享数据时,可以保证内存中的数据可见性.
 *                        相较于 synchronized 是一种轻量级的同步策略
 *
 *                        1.不具备互斥性
 *                        2.不能保证原子性
 * @date 2020-06-14-14:00
 */
public class VolatileTest {

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();


        while (true) {
            if (threadDemo.isFlag()) {
                System.out.println("-------------------------------------");
                break;
            }
        }
    }

}
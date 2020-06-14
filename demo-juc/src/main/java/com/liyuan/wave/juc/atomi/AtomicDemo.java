package com.liyuan.wave.juc.atomi;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liyuan
 * @description
 * @date 2020-06-14-14:37
 */
public class AtomicDemo implements Runnable{

//    private int serialNumber = 0;

    private AtomicInteger serialNumber = new AtomicInteger();

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }

        System.out.println(getSerialNumber());
    }

    public int getSerialNumber() {
//        return serialNumber++;
        return serialNumber.getAndIncrement();
    }
}

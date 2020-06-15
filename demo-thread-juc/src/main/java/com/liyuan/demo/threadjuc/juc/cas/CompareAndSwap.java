package com.liyuan.demo.threadjuc.juc.cas;

/**
 * @author liyuan
 * @description
 * @date 2020-06-14-14:56
 */
public class CompareAndSwap {

    private int value;

    public synchronized int get(){
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue,int newValue){
        int oldValue = value;

        if(oldValue == expectedValue){
            this.value = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue,int newValue){
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}

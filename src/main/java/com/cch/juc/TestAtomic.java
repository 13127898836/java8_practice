package com.cch.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cch
 * 2018-05-05 11:35.
 */

public class TestAtomic {
    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        for (int i = 0; i < 10; i++) {
            new Thread(threadB).start();
        }
    }
}
class ThreadB implements Runnable{
    //private volatile int number;
    private AtomicInteger number;
    @Override
    public void run() {
        System.out.println(getNumber());
    }

    public int getNumber() {
        return number.getAndIncrement();
    }
}

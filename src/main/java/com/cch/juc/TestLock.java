package com.cch.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cch
 * 2018-05-05 20:01.
 */

public class TestLock {
    public static void main(String[] args) {
      ThreadE threadE = new ThreadE();
      new Thread(threadE).start();
      new Thread(threadE).start();
      new Thread(threadE).start();
    }
}
class ThreadE implements Runnable{
    private int money=100;
    private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        lock.lock();
        try {
            while (money > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                money = money - 5;
                System.out.println(money);
            }
        }finally {
            lock.unlock();
        }
    }
}

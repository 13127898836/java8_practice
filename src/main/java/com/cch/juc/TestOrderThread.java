package com.cch.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程 循环打印ABC
 * Created by cch
 * 2018-05-05 20:58.
 */

public class TestOrderThread {
    public static void main(String[] args) {
        OrderDemo orderDemo = new OrderDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        orderDemo.printA();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"threadA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        orderDemo.printB();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"threadB").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        orderDemo.printC();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"threadC").start();
    }
}
class OrderDemo{
    private int num =1;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA() throws InterruptedException {
        lock.lock();
        try {
            if (num != 1) {
                conditionA.await();
            }
                System.out.println(Thread.currentThread().getName() + " A " );
            num =2;
            conditionB.signal();
        }finally {
            lock.unlock();
        }
    }

    public void printB() throws InterruptedException {
        lock.lock();
        try {
            if (num != 2) {
                conditionB.await();
            }
                System.out.println(Thread.currentThread().getName() + " B " );
            num=3;
            conditionC.signal();
        }finally {
            lock.unlock();
        }
    }

    public void printC() throws InterruptedException {
        lock.lock();
        try {
            if (num != 3) {
                conditionC.await();
            }
                System.out.println(Thread.currentThread().getName() + " C " );
            num=1;
            conditionA.signal();
        }finally {
            lock.unlock();
        }
    }
}

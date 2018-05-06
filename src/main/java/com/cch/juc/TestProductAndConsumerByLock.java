package com.cch.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * Created by cch
 * 2018-05-05 20:27.
 */

public class TestProductAndConsumerByLock {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        new Thread(new GetThread(clerk),"get1").start();
        new Thread(new GetThread(clerk),"get2").start();
        new Thread(new GetThread(clerk),"get3").start();
        new Thread(new SaleThread(clerk),"sale1").start();
        new Thread(new SaleThread(clerk),"sale2").start();
        new Thread(new SaleThread(clerk),"sale3").start();
    }
}
//店员
class Clerk{
    private int product =0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    //进货
    public void get(){
        lock.lock();
        try {
            while (product >= 1) {
                System.out.println("商品已满");
                try {
                    condition.await();
                } catch (Exception e) {

                }
            }
            System.out.println(Thread.currentThread().getName() + " product:" + ++product);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void sale(){
        lock.lock();
        try {
            while (product <= 0) {
                System.out.println("缺货");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " product:" + --product);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

}

class GetThread implements Runnable{
    private Clerk clerk;
    public GetThread(Clerk clerk) {
        this.clerk = clerk;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}

class SaleThread implements Runnable{
    private Clerk clerk;

    public SaleThread(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.sale();
        }
    }
}

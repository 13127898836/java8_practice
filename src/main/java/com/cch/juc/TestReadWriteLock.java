package com.cch.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 写写 写读 互斥
 * 读读不互斥
 * Created by cch
 * 2018-05-06 13:57.
 */

public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteDemo demo = new ReadWriteDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.set((int)(Math.random()*1000));
                }
            },"write").start();
        }
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.get();
                }
            },"read").start();
        }

    }
}
class  ReadWriteDemo{
    private int  num;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void get(){
        lock.readLock().lock();
        try{
            //Thread.sleep(1);
            System.out.println(Thread.currentThread().getName()+" "+num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void set(int num){
        lock.writeLock().lock();
        try{
            Thread.sleep(1000);
            this.num = num;
            System.out.println(Thread.currentThread().getName()+" "+num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}

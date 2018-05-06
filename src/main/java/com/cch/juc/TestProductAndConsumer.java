package com.cch.juc;

/**
 * wait 要放在while 循环内，不要放在if方法体内，要不然会出现虚假唤醒
 * Created by cch
 * 2018-05-05 20:27.
 */

public class TestProductAndConsumer {
    public static void main(String[] args) {
       /* Clerk clerk = new Clerk();
        new Thread(new GetThread(clerk),"get1").start();
        new Thread(new GetThread(clerk),"get2").start();
        new Thread(new GetThread(clerk),"get3").start();
        new Thread(new SaleThread(clerk),"sale1").start();
        new Thread(new SaleThread(clerk),"sale2").start();
        new Thread(new SaleThread(clerk),"sale3").start();*/
    }
}
//店员
/*
class Clerk{
    private int product =0;
    //进货
    public synchronized void get(){
        while(product>=1){
            System.out.println("商品已满");
            try{
                this.wait();
            }catch (Exception e){

            }
        }
        System.out.println(Thread.currentThread().getName()+" product:"+ ++product);
        this.notifyAll();
    }

    public synchronized void sale(){
        while(product<=0){
            System.out.println("缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+" product:"+ --product);
        this.notifyAll();
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
}*/

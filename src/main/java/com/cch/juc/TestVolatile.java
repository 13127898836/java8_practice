package com.cch.juc;

/**
 * volatile可以解决可见性问题
 * 但是 不具备 互斥性 和原子性
 * Created by cch
 * 2018-05-05 11:00.
 */

public class TestVolatile {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        new Thread(threadA).start();
        while (true){

                if (threadA.isFlag()) {
                    System.out.println("main --------------------");
                    break;
                }


        }
    }

}

class ThreadA implements Runnable {
    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(1000);
            flag=true;
            System.out.println("a flag="+flag);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

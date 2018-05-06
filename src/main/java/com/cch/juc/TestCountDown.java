package com.cch.juc;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

/**
 * Created by cch
 * 2018-05-05 19:18.
 */

public class TestCountDown {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        Instant start = Instant.now();
        for(int i=0;i<10;i++) {
            ThreadC threadC = new ThreadC(latch);
            new Thread(threadC).start();
        }
        latch.await();
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
    }
}

class ThreadC implements  Runnable{
    private CountDownLatch latch;

    public ThreadC(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        long sum =0;
        for (int i = 0; i < Integer.MAX_VALUE/5; i++) {
            sum+=i;
        }
        latch.countDown();
    }
}


package com.cch.juc;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by cch
 * 2018-05-05 19:36.
 */

public class TestCallable {
    public static void main(String[] args) {
        ThreadD threadD = new ThreadD();
        FutureTask<Long> futureTask = new FutureTask<Long>(threadD);
        new Thread(futureTask).start();


        try {
            Instant start = Instant.now();
            long sum = futureTask.get();
            Instant end = Instant.now();
            System.out.println(Duration.between(start,end).toMillis()+" sum:"+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
class ThreadD implements Callable<Long>{
    @Override
    public Long call() throws Exception {
        long sum=0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum+=i;
        }
        return sum;
    }
}


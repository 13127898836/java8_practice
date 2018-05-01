package com.cch.java8.stream;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * 计算1到10000000000值得和
 * Created by cch
 * 2018-04-30 23:27.
 */

public class TestForkJoin {
    /**
     * 普通for循环
     * 100000000L 45
     * 1000000000L 472
     * 10000000000L 4294
     * 100000000000L 29172
     */
    @Test
    public void test1(){
       long end1 = 100000000L;
       long end2 = 1000000000L;
       long end3 = 10000000000L;
       long end4 = 100000000000L;
       long sum=0;
        Instant start = Instant.now();
        for(long i=0;i<end4;i++){
           sum+=i;
       }
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
        System.out.println(sum);
    }

    /**
     * ForkJoin
     * 100000000L 73
     * 1000000000L 294
     * 10000000000L 1898
     * 100000000000L 15679
     */
    @Test
    public void test2(){
        long end1 = 100000000L;
        long end2 = 1000000000L;
        long end3 = 10000000000L;
        long end4 = 100000000000L;
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task =new ForJoinCalculate(0,end4);
        Long sum =pool.invoke(task);
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
        System.out.println(sum);
    }

    /**
     * java 并行流
     * 100000000L 108
     * 1000000000L 228
     * 10000000000L 1647
     * 100000000000L 13387
     */
    @Test
    public void test3(){
        long end1 = 100000000L;
        long end2 = 1000000000L;
        long end3 = 10000000000L;
        long end4 = 100000000000L;
        Instant start = Instant.now();
        long sum = LongStream.rangeClosed(0, end4)
                .parallel()
                .reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
        System.out.println(sum);
    }
}

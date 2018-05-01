package com.cch.java8.datatime;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by cch
 * 2018-05-01 22:00.
 */

public class TestSimpleDateFormat {
    /**
     * SimpleDateFormat 存在多线程安全问题
     */
    @Test
    public void test()throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                //return sdf.parse("20180501");
                return DateFormatThreadLocal.convert("20180501");
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<Date>> resultList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            resultList.add(pool.submit(task));
        }
        for(Future<Date> future: resultList){
            System.out.println(future.get());
        }
        pool.shutdown();
    }
    @Test
    public void test2()throws Exception{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                //return sdf.parse("20180501");
                return LocalDate.parse("20180501",dtf);
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> resultList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            resultList.add(pool.submit(task));
        }
        for(Future<LocalDate> future: resultList){
            System.out.println(future.get());
        }
        pool.shutdown();
    }
}

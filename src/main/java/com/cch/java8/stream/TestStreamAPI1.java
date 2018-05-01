package com.cch.java8.stream;

import com.cch.java8.lambda.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream的三个操作步骤
 * 1创建stream
 * 2中间操作
 * 3终止操作
 * Created by cch
 * 2018-04-30 10:42.
 */

public class TestStreamAPI1 {
    @Test
    public void test1(){
        //1通过Collection系列集合提供的stream()或 paralleStream()
        List<String> list = new ArrayList<String>();
        Stream<String> stream1 = list.stream();
        Stream<String> stringStream = list.parallelStream();

        //2通过 Arrays中的静态方法stream()
        Student[] studentArray = new Student[10];
        Stream<Student> stream2 = Arrays.stream(studentArray);

        //3通过Stream类的静态方法of()
        Stream<String> stream3 = Stream.of("a", "b");

        //4无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(2, (x) -> x +2);
        stream4.limit(5).forEach(System.out::println);

        //生成
        Stream<Double> stream5 = Stream.generate(() -> Math.random());
        stream5.limit(5).forEach(System.out::println);

    }
}

package com.cch.java8.lambda;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Consumer<T> :消费型接口
 *          void <T> accept（T t）
 * Supplier<T> :供给型接口
 *          T get()
 * Fuction<T,R>:函数型接口
 *          R apply(T t)
 * Predicate<T>:断言型接口
 *          boolean test（T t）
 * Created by cch
 * 2018-04-29 21:17.
 */

public class LambdaTest2 {

    public void accept(long money, Consumer<Long> consumer){
        consumer.accept(money);
    }

    /**
     * 消费
     */
    @Test
    public void consumer(){
        accept(100,(e)-> System.out.println("消费了"+e+"元"));
    }

    public Integer get(Supplier<Integer> supplier){
        return supplier.get();
    }

    /**
     * 供给
     */
    @Test
    public void supplier(){
        int value = get(()-> (int)(Math.random()*100));
        System.out.println(value);
    }

    public String apply(String source, Function<String, String> func){
        return func.apply(source);
    }

    /**
     * 函数
     */
    @Test
    public void function(){
        String source = "cch";
        String result = apply(source, (k) -> k.toUpperCase());
        System.out.println(result);
    }

    public boolean test(Student student, Predicate<Student> predicate){
       return  predicate.test(student);
    }

    /**
     * 断言
     */
    @Test
    public void predicate(){
        boolean result = test(new Student("cch", 13, 99), (e) -> e.getAge() > 10);
        System.out.println(result);
    }
}

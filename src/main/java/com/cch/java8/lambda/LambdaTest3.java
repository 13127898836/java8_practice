package com.cch.java8.lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 一、方法引用与构造器引用
 * 对象::实例方法
 * 类::静态方法
 * 类::实例方法
 *
 * java8 体中调用方法的参数列表和返回类型要和函数式接口中抽象方法的函数列表和返回值类型保持一致
 * 若 java8 参数列表中的第一个参数是 实例方法的调用者，第二个参数是实例方法的参数时，可以使用ClassName::method
 *
 * 二、构造器引用：
 *  格式：
 *  ClassName::new
 *  需要调用的构造器的参数列表要和抽象方法的参数列表一致
 *
 * 三、数组引用：
 *  Type::new
 * Created by cch
 * 2018-04-29 22:50.
 */

public class LambdaTest3 {
    /**
     * 对象::实例方法 1
     */
    @Test
    public void test1(){
        PrintStream ps = System.out;
        Consumer<String> con1 = (e)-> ps.println(e);
        //新语法
        Consumer<String> con2 = ps::println;
        Consumer<String> con3 = System.out::println;
        con3.accept("hello world");
    }
    /**
     * 对象::实例方法 2
     */
    @Test
    public void test2(){
        Student student = new Student("zhangsan",13,100);
        Supplier<String> sup = ()->student.getName();
        String str = sup.get();
        System.out.println(str);
        //新语法
        Supplier<Integer> sup2 = student::getAge;
        Integer score = sup2.get();
        System.out.println(score);
    }

    /**
     * 类::静态方法
     */
    @Test
    public void test3(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        //新语法
        Comparator<Integer> com1 = Integer::compare;
        int compare = com1.compare(1, 2);
        System.out.println(compare);
    }

    /**
     * 类::实例方法
     */
    @Test
    public void test4(){
        BiPredicate<String,String> bp = (x,y) ->x.equals(y);
        //新语法
        BiPredicate<String,String> bp1 = String::equals;
    }

    /**
     * 构造器引用
     */
    @Test
    public void test5(){
        Supplier<Student> sup1 = ()-> new Student("zhangsan",1,100);
        //新语法
        Supplier<Student> sup2 =Student::new;//无参构造器
    }

    /**
     * 构造器引用 有参
     */
    @Test
    public void test6(){
        Function<Integer,Student> func1 = Student::new;
        BiFunction<String,Integer,Student> func2 = Student::new;
        Student zhangsan = func2.apply("zhangsan", 10);
        System.out.println(zhangsan);
    }

    /**
     * 数组引用
     */
    @Test
    public void test7(){
        Function<Integer,String[]> func1 = (x)->new String[x];
        String[] strs1 = func1.apply(5);
        System.out.println(strs1.length);
        //新语法
        Function<Integer,String[]> func2 = String[]::new;
        String[] strs2 = func2.apply(10);
        System.out.println(strs2.length);
    }
}

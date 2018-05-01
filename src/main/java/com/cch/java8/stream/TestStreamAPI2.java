package com.cch.java8.stream;

import com.cch.java8.lambda.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 中间操作
 * 筛选和切片
 * 映射
 * Created by cch
 * 2018-04-30 20:24.
 */

public class TestStreamAPI2 {
    List<Student> studentList =null;
    @Before
    public void befor(){
        studentList = Arrays.asList(
                new Student("zhangsan",15,93),
                new Student("lisi",16,100),
                new Student("wangwu",17,86),
                new Student("zhaoliu",13,71),
                new Student("yuanqi",13,65)
        );
    }

    /**
     * 筛选和切片
     * filter 接收lambda，从流中排除某些元素
     * limit 截断流，使其元素不穿过给定数量
     * skip(n) 跳过元素，返回一个丢掉前n个元素的流，若流不足n个，返回一个空流，与limit互补
     * distinct 去重，通过元素的hashCode() 和 equals()比较去重
     */

    /**
     * filter
     * 内部迭代：迭代操作由StreamAPI完成
     */
    @Test
    public void test1(){
        //中间操作 不会执行任何操作
        Stream<Student> stream = studentList.stream()
                .filter((e) -> {
                    System.out.println("中间操作");
                    return e.getAge() > 13;
                });
        System.out.println("-----------------------------------------");
        //终止操作 一次性执行全部内容，即“惰性求值”
        stream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test2(){
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    /**
     * 截断流 一旦找到满足条件的数据，后续的迭代结束
     */
    @Test
    public void test3(){
        studentList.stream()
                .filter((e)->{
                    System.out.println("短路！");
                    return e.getScore()>60;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    /**
     * 跳过
     */
    @Test
    public void test4(){
        studentList.stream()
                .filter(e->e.getScore()>60)
                .skip(2)
                .forEach(System.out::println);
    }

    /**
     * 去重
     */
    @Test
    public void test5(){
        studentList.stream()
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射
     * map 接收lambda 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成为一个新的元素
     * flatMap 接收一个函数为参数，将流中的每个值换成另一个流，然后把所有的流连接成一个流
     */
    @Test
    public void test6(){
        List<String> list = Arrays.asList("aaa","bbb","ccc");
        list.stream()
                .map((e)->e.toUpperCase())
                .forEach(System.out::println);

        System.out.println("--------------------");

        studentList.stream()
                .map(e->e.getName())
                .forEach(System.out::println);

        System.out.println("----------------------");

        Stream<Stream<Character>> streamStream = list.stream()
                .map(TestStreamAPI2::filterCharcater);
        streamStream.forEach((e)->e.forEach(System.out::print));

        System.out.println("------------------");
        list.stream()
                .flatMap(TestStreamAPI2::filterCharcater)
                .forEach(System.out::print);

    }

    public static Stream<Character> filterCharcater(String str){
        List<Character> list = new ArrayList<>();
        for(Character character :str.toCharArray()){
            list.add(character);
        }
        return list.stream();
    }


    /**
     * 排序
     * sorted() 自然排序
     * sorted(Compator com) 定制排序
     */
    @Test
    public void test7(){
        List<String> list = Arrays.asList("cc","a","df","ab","ca");
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("---------------------------");
        studentList.stream()
                .sorted((e1,e2)->{
                    if(e1.getAge()==e1.getAge()){
                        return e1.getName().compareTo(e2.getName());
                    }else{
                        return Integer.compare(e1.getAge(),e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }
}

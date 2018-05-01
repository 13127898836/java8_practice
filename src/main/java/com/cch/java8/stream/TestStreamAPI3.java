package com.cch.java8.stream;

import com.cch.java8.lambda.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 * Created by cch
 * 2018-04-30 21:04.
 */

public class TestStreamAPI3 {
    List<Student> studentList =null;
    @Before
    public void befor(){
        studentList = Arrays.asList(
                new Student("zhangsan",15,93, Student.Status.BUSY),
                new Student("lisi",16,100, Student.Status.FREE),
                new Student("wangwu",17,86, Student.Status.VOCATION),
                new Student("zhaoliu",13,71, Student.Status.FREE),
                new Student("yuanqi",13,65, Student.Status.FREE)
        );
    }

    /**
     * 查找与匹配
     * allMatch 检查是否匹配所有元素
     * anyMacth 检查是否至少匹配一个元素
     * noneMacth 检查是否没有匹配所有元素
     * findFirst 返回第一个元素
     * findAny 返回任意一个元素
     * count 返回元素总数
     * max 返回最大值
     * min 返回最小值
     */

    @Test
    public void test1(){
        boolean b1 = studentList.stream()
                .allMatch((e) -> e.getStatus() == Student.Status.FREE);
        System.out.println(b1);

        boolean b2 = studentList.stream()
                .anyMatch((e) -> e.getStatus() == Student.Status.FREE);
        System.out.println(b2);

        boolean b3 = studentList.stream()
                .noneMatch((e) -> e.getStatus() == Student.Status.FREE);
        System.out.println(b3);

        Optional<Student> op1 = studentList.stream()
                .sorted((e1, e2) -> -Integer.compare(e1.getScore(), e2.getScore()))
                .findFirst();
        System.out.println(op1.get());

        Optional<Student> op2 = studentList.stream()
                .filter((e) -> e.getStatus() == Student.Status.FREE)
                .findAny();
        System.out.println(op2.get());
        //并行流
        Optional<Student> op3 = studentList.parallelStream()
                .filter((e) -> e.getStatus() == Student.Status.FREE)
                .findAny();
        System.out.println(op3.get());
        //count
        long count = studentList.stream()
                .count();
        System.out.println(count);
        //max
        Optional<Student> max = studentList.stream()
                .max(Comparator.comparing(Student::getName));
        System.out.println(max.get());
        //min
        Optional<Integer> min = studentList.stream()
                .map(e -> e.getScore())
                .min(Integer::compare);
        System.out.println(min.get());

    }

    /**
     * 归约
     * reduce(T identity,BinaryOperator) /reduce(BinaryOperator) 将流中元素反复结合起来 得到一个值
     */

    @Test
    public void test2(){
        List<Integer> lsit = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = lsit.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        System.out.println("--------------");

        Integer sum2 = studentList.stream()
                .map(Student::getScore)
                .reduce(0, (x, y) -> x + y);
        System.out.println("分数总和"+sum2);

    }

    /**
     * 收集
     * collect 将流转换为其他形式，接收一个Collector接口的实现，用于将Stream重点额元素做汇总的方法
     */
    @Test
    public void test3(){
        List<String> collect = studentList.stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("----------------");
        Set<String> collect1 = studentList.stream()
                .map(Student::getName)
                .collect(Collectors.toSet());
        System.out.println(collect1);
        System.out.println("------------");
        HashSet<String> collect2 = studentList.stream()
                .map(Student::getName)
                .collect(Collectors.toCollection(HashSet::new));
    }
    @Test
    public void test4(){
        //总数
        Long count = studentList.stream()
                .collect(Collectors.counting());
        System.out.println(count);
        System.out.println("-------------");
        //平均数
        Double avg = studentList.stream()
                .collect(Collectors.averagingInt(Student::getScore));
        System.out.println(avg);
        System.out.println("-----------------");
        //总和
        Integer sum = studentList.stream()
                .collect(Collectors.summingInt(Student::getScore));
        System.out.println(sum);
        System.out.println("-----------------");

        //最大值
        Optional<Student> max = studentList.stream()
                .collect(Collectors.maxBy((e1, e2) -> Integer.compare(e1.getScore(), e2.getScore())));
        System.out.println(max.get());
        System.out.println("------------");
        //最小值

        Optional<Integer> min = studentList.stream()
                .map(Student::getScore)
                .collect(Collectors.minBy(Integer::compare));
        System.out.println(min);
        System.out.println("-----------------");
    }

    /**
     * 分组
     */
    @Test
    public void test5(){
        Map<Student.Status, List<Student>> map = studentList.stream()
                .collect(Collectors.groupingBy(Student::getStatus));
        System.out.println(map);
    }
    @Test
    public void test6(){
        Map<Student.Status, Map<String, List<Student>>> map = studentList.stream()
                .collect(Collectors.groupingBy(Student::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() < 15) {
                        return "小朋友";
                    } else {
                        return "青年";
                    }
                })));
        System.out.println(map);
    }

    /**
     * 分区
     */
    @Test
    public void test7(){
        Map<Boolean, List<Student>> map = studentList.stream()
                .collect(Collectors.partitioningBy((e) -> e.getScore() > 70));
        System.out.println(map);
    }
    @Test
    public void test8(){
        IntSummaryStatistics iss = studentList.stream()
                .collect(Collectors.summarizingInt(Student::getScore));
        System.out.println(iss.getSum());
        System.out.println(iss.getAverage());
        System.out.println(iss.getCount());
        System.out.println(iss.getMax());
    }
    @Test
    public void test9(){
        String str = studentList.stream()
                .map(Student::getName)
                .collect(Collectors.joining(","));
        System.out.println(str);
    }
}

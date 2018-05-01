package com.cch.java8.lambda;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by cch
 * 2018-04-29 11:25.
 */

public class LambdaTest1 {
    List<Student> studentList =null;
    @Before
   public void befor(){
       studentList = Arrays.asList(
                new Student("zhangsan",15,93),
                new Student("lisi",16,100),
                new Student("wangwu",17,86),
                new Student("zhaoliu",13,71),
                new Student("yuanqi",14,65)
        );
   }

    /**
     * 查找成绩>70的学生
     */
   @Test
   public void test1(){
       List<Student> dataList = new ArrayList<>();
       for(Student student :studentList){
           if(student.getScore()>70){
               dataList.add(student);
           }
       }
       for(Student student :dataList){
           System.out.println(student);
       }
   }

    /**
     * 查找年龄>14的学生
     */
    @Test
    public void test2(){
        List<Student> dataList = new ArrayList<>();
        for(Student student :studentList){
            if(student.getAge()>14){
                dataList.add(student);
            }
        }
        for(Student student :dataList){
            System.out.println(student);
        }
    }
    /**
     * 优化1 策略模式
     */
    public List<Student> getStudentByFilter(List<Student> studentList ,StudentFilter studentFilter){
        List<Student> data = new ArrayList<>();
        for(Student student:studentList){
            if(studentFilter.compare(student)){
                data.add(student);
            }
        }
        return data;
    }

    public void printStudentList(List<Student> studentList){
        for(Student student:studentList){
            System.out.println(student);
        }
    }

    /**
     * 优化1之后 查找成绩>70的学生
     */
    @Test
    public void test3(){
        List<Student> studentByFilter = getStudentByFilter(studentList, new StudentFilterByScore());
        printStudentList(studentByFilter);
    }

    /**
     * 优化1之后 查找年龄>14的学生
     */
    @Test
    public void test4(){
        List<Student> studentByFilter = getStudentByFilter(studentList, new StudentFilterByAge());
        printStudentList(studentByFilter);
    }

    /**
     * 继续优化2 采用匿名内部类查找成绩>70的学生
     */
    @Test
    public void test5(){
        List<Student> studentByFilter = getStudentByFilter(studentList, new StudentFilter(){
            @Override
            public boolean compare(Student student) {
                return student.getScore()>70;
            }
        });
        printStudentList(studentByFilter);
    }

    /**
     * 继续优化2 采用匿名内部类查找年龄>14的学生
     */
    @Test
    public void test6(){
        List<Student> studentByFilter = getStudentByFilter(studentList, new StudentFilter(){
            @Override
            public boolean compare(Student student) {
                return student.getAge()>14;
            }
        });
        printStudentList(studentByFilter);
    }

    /**
     * 优化3 java8 查找年龄>14的学生
     */
    @Test
    public void test7(){
        List<Student> studentByFilter =getStudentByFilter(studentList,(e)->e.getAge()>14);
        studentByFilter.forEach(System.out::println);
    }

    /**
     * 优化3 java8 查找成绩>70的学生
     */
    @Test
    public void test8(){
        List<Student> studentByFilter =getStudentByFilter(studentList,(e)->e.getScore()>70);
        studentByFilter.forEach(System.out::println);
    }

    /**
     * 优化4 StreamAPI 查找年龄>14的学生
     */
    @Test
    public void test9(){
        studentList.stream()
                .filter(student -> student.getAge()>14)
                .forEach(System.out::println);
    }

    /**
     * 优化4 StreamAPI 查找成绩>70的学生
     */
    @Test
    public void test10(){
        studentList.stream()
                .filter(student -> student.getScore()>70)
                .forEach(System.out::println);
    }
}

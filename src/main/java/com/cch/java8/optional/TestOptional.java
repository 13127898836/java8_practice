package com.cch.java8.optional;

import com.cch.java8.lambda.Student;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by cch
 * 2018-05-01 20:03.
 */

public class TestOptional {
    /*
     * 一、Optional 容器类：用于尽量避免空指针异常
     * 	Optional.of(T t) : 创建一个 Optional 实例
     * 	Optional.empty() : 创建一个空的 Optional 实例
     * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
     * 	isPresent() : 判断是否包含值
     * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
     * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
     * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
     * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
     */
    @Test
    public void test1() {
        Optional<Student> optional = Optional.of(new Student());
        System.out.println(optional.get());
    }

    @Test
    public void test2() {
        Optional<Student> op1 = Optional.ofNullable(null);
        System.out.println(op1.get());
        Optional<Student> op2 = Optional.empty();
        System.out.println(op2.get());
    }

    @Test
    public void test3() {
        Optional<Student> op1 = Optional.ofNullable(null);
        if (op1.isPresent()) {
            System.out.println(op1.get());
        }
        Student student = op1.orElse(new Student("cch", 15, 99, Student.Status.BUSY));
        System.out.println(student);
        Student student1 = op1.orElseGet(() -> new Student());
        System.out.println(student1);

    }

    @Test
    public void test4() {
        Optional<Student> op1 = Optional.ofNullable(new Student("cch", 15, 99, Student.Status.BUSY));
        Optional<Integer> op2 = op1.map(Student::getAge);
        System.out.println(op2.get());
        Optional<String> op3 = op1.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(op3.get());
    }

    public String getTeacherName(Student student) {
        if (student != null) {
            Teacher teacher = student.getTeacher();
            if (teacher != null) {
                return teacher.getName();
            }
        }
        return "苍老师";
    }

    @Test
    public void test5() {
        Student student = new Student();
        String teacherName = getTeacherName(student);
        System.out.println(teacherName);
    }

    public String getTeacherName2(Optional<NewStudent> optional) {
        return optional.orElse(new NewStudent())
                .getTeacherOpt()
                .orElse(new Teacher("苍老师"))
                .getName();
    }

    @Test
    public void test6() {
        Optional<Teacher> teacherOptional = Optional.ofNullable(null);
        NewStudent student = new NewStudent(teacherOptional);
        Optional<NewStudent> op = Optional.ofNullable(student);
        String teacherName = getTeacherName2(op);
        System.out.println(teacherName);
    }
}

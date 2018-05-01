package com.cch.java8.lambda;

/**
 * Created by cch
 * 2018-04-29 11:42.
 */

public class StudentFilterByAge implements StudentFilter{
    @Override
    public boolean compare(Student student) {
        return student.getAge()>14;
    }
}

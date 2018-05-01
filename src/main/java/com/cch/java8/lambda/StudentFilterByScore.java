package com.cch.java8.lambda;

/**
 * Created by cch
 * 2018-04-29 11:38.
 */

public class StudentFilterByScore  implements StudentFilter{
    @Override
    public boolean compare(Student student) {
        return student.getScore()>70;
    }
}

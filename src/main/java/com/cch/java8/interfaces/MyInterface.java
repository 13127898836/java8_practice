package com.cch.java8.interfaces;

/**
 * Created by cch
 * 2018-05-01 21:17.
 */

public interface MyInterface {
    default String getName() {
        return "呵呵呵";
    }

    static String helloWorld() {
        return "hello world";
    }
}

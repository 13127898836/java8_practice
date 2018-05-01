package com.cch.java8.annotation;

import org.junit.Test;

import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Created by cch
 * 2018-05-01 21:37.
 */

public class TestAnnotation {
    @MyAnnotation("hello")
    @MyAnnotation("word")
    public void test(){

    }
    @Test
    public void test2() throws Exception {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method test = clazz.getMethod("test");
        MyAnnotation[] annotationsByType = test.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation annotation:annotationsByType){
            System.out.println(annotation.value());
        }

    }
}

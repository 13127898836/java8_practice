package com.cch.java8.annotation;

import java.lang.annotation.*;

/**
 * Created by cch
 * 2018-05-01 21:33.
 */
@Repeatable(MyAnnotations.class)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
     String value() default "null";
}

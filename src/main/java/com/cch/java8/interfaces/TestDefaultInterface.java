package com.cch.java8.interfaces;

import com.cch.java8.optional.SubClass;
import org.junit.Test;

/**
 * Created by cch
 * 2018-05-01 21:14.
 */

public class TestDefaultInterface {
    /**
     * 当接口和父类中的方法冲突，优先使用父类的方法
     * 当多个接口中有方法冲突，必须去实现一个
     * 接口中允许使用静态方法
     */
    @Test
    public void test(){
        SubClass subClass = new SubClass();
        String name = subClass.getName();
        System.out.println(name);
    }
}

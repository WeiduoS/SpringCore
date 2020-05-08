package com.web.manully.cglib;

/**
 * @author Weiduo
 * @date 2020/5/7 - 1:03 AM
 */
public class MyAspect {
    public void before(){
        System.out.println("cglib之目标方法执行前");
    }

    public void after(){
        System.out.println("cglib之目标方法执行后 ");
    }

}

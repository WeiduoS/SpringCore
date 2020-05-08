package com.web.manully.jdk;

/**
 * @author Weiduo
 * @date 2020/5/6 - 8:38 PM
 */
public class MyAspect {
    public void before(){
        System.out.println("jdk_proxy之目标方法执行前 ");
    }

    public void after(){
        System.out.println("jdk_proxy之目标方法执行后");
    }
}

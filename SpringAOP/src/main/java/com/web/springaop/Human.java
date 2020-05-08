package com.web.springaop;

/**
 * @author Weiduo
 * @date 2020/5/7 - 2:03 AM
 */
public class Human implements Sleepable {

    @Override
    public void sleep() {
        System.out.println("我要睡觉了！");
    }
}
package com.web.springaop;

/**
 * @author Weiduo
 * @date 2020/5/7 - 2:18 AM
 */
public class SleepHelperAspect {
    public void beforeSleep(){
        System.out.println("睡觉前要脱衣服！");
    }

    public void afterSleep(){
        System.out.println("起床后要穿衣服！");
    }
}

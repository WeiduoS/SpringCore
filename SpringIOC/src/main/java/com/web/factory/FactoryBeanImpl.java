package com.web.factory;

import com.web.beans.Homework;
import org.springframework.beans.factory.FactoryBean;

/**
 * 实现了FactoryBean的类是Spring可以认识的工厂类
 * Spring会自动的调用工厂方法创建实例
 * @author Weiduo
 * @date 2020/5/5 - 2:38 AM
 */
public class FactoryBeanImpl implements FactoryBean<Homework> {

    /**
     * 返回创建对象
     * @return
     * @throws Exception
     */
    public Homework getObject() throws Exception {
        System.out.println("通过FactoryBean创建了一个homework对象");
        return new Homework("some homework !!!");
    }

    /**
     * 返回创建对象的类型
     * @return
     */
    public Class<?> getObjectType() {
        return Homework.class;
    }

    /**
     * 返回对象是否单例
     * @return
     */
    public boolean isSingleton() {
        return true;
    }
}

package com.web;

import com.web.beans.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Weiduo
 * @date 2020/5/6 - 12:18 AM
 */
class ApplicationTest {

    /**
     * InstantiationAwareBeanPostProcessor
     * 1. 需要继承InstantiationAwareBeanPostProcessorAdapter父类
     * 2. 三个方法可能需要使用
     *      a. postProcessBeforeInstantiation(Class beanClass,String beanName): 初始化之前调用, 从signature中可以看出
     *          此时可以获取到类信息和bean的名字
     *      b. postProcessAfterInitialization(Object bean, String beanName): 初始化结束时调用, 这时可以拿到bean了
     *      c. @deprected
     *         postProcessPropertyValues(PropertyValues pvs,PropertyDescriptor[] pds, Object bean, String beanName)
     *         postProcessProperties(PropertyValues pvs, Object bean, String beanName): 因为这时只是初始化类的基本信息, 这个初始化
     *         比我们平时new出来的类还原始, 因为我们自己new出来的类一般带有需要的基本信息. 而这里不同, 我们需要借助Spring调用Setter Getter方法
     *         帮助我们初始化. 而这个方法调用是在Setter Getter方法之前.
     *         因此我们可以在这里对类进行一些基本的赋值, 注意不要和之后的赋值有冲突, 不然会被覆盖
     */
    @Test
    public void text01() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        System.out.println(ioc.getBean("instantiationAwareBeanPostProcessor"));
    }

    /**
     * BeanPostProcessor
     * 1. 需要实现BeanPostProcessor接口
     * 2. 俩个方法可能会用到
     *      a. postProcessBeforeInitialization(Object bean, String beanName): 初始化之前进行赋值
     *      b. postProcessAfterInitialization(Object bean, String beanName): 初始化之后进行赋值
     */
    @Test
    public void text02() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext02.xml");
        System.out.println(ioc.getBean("beanPostProcessor"));
    }

    /**
     * BeanFactoryPostProcessor
     * bean工厂的bean属性处理容器，说通俗一些就是可以管理我们的bean工厂内所有的beandefinition（未实例化）数据，可以随心所欲的修改属性。
     *
     * 我们需要自行实现BeanFactoryPostProcessor的postProcessBeanFactory方法
     *
     * public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
     *      public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
     *          System.out.println("调用了自定义的BeanFactoryPostProcessor " + beanFactory);
     *          Iterator it = beanFactory.getBeanNamesIterator();
     *
     *          String[] names = beanFactory.getBeanDefinitionNames();
     *          // 获取了所有的bean名称列表
     *          for(int i=0; i<names.length; i++){
     *              String name = names[i];
     *
     *              BeanDefinition bd = beanFactory.getBeanDefinition(name);
     *              System.out.println(name + " bean properties: " + bd.getPropertyValues().toString());
     *              // 本内容只是个demo，打印持有的bean的属性情况
     *          }
     *      }
     * }
     */
    @Test
    public void text03() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext03.xml");
        System.out.println(ioc.getBean("beanFactoryPostProcessor"));
    }


    /**
     * BeanFactoryAware: 可以获得创造bean的工厂
     * BeanNameAware: 可以获得bean name
     * InitializingBean: 初始化bean
     * DisposableBean: 销毁bean
     */
    @Test
    public void text04() {
        System.out.println("现在开始初始化容器");
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext04.xml");

        //得到Preson，并使用
        Person person = ioc.getBean("person",Person.class);
        System.out.println(person);

        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)ioc).registerShutdownHook();
    }

}
package com.web;

import com.web.beans.Homework;
import com.web.beans.Student;
import com.web.controller.ControllerDemo;
import com.web.service.ServiceDemo;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Weiduo
 * @date 2020/5/4 - 12:14 AM
 */

class ApplicationTest {

    // 一、IOC创建对象的
    /**
     * 通过id寻找bean
     */
    @Test
    public void text01() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring.xml");
        System.out.println(ioc.getBean("homework"));
        System.out.println(ioc.getBean("student"));
    }


    /**
     * 通过类型寻找bean
     * tip:如果有ioc有两个相同类型的bean查找会失败
     */
    @Test
    public void text02() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring.xml");
        System.out.println(ioc.getBean(Homework.class));
    }


    /**
     * 通过构造器创造bean
     *      1）第一种: 下标赋值:  <constructor-arg index="0" value="zhangsan"/>
     *      2）第二种: 类型赋值:  <constructor-arg type="java.lang.String" value="zhangsan"/>  不能处理两个参数类型一样的情况
     *      3）第三种: 直接通过参数名赋值 <constructor-arg name="name" value="zhangsan"/>
     */
    @Test
    public void text03() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring.xml");
        System.out.println(ioc.getBean("homework02"));
    }


    /**
     * 3、利用工厂创建对象
     *      静态工厂：工厂本身不需要创建对象；通过静态方法调用，对象=工厂类.工厂方法名()
     *          使用方法：
     *              class: 指定工厂全类名
     *              factory-method: 指定工厂方法
     *              constructor-arg: 可以为方法传参
     *              <bean id="airPlane01" class="com.lxy.factory.AirPlaneStaticFactory" factory-method="getAirPlane">
     * 　　　　　　　　　　<constructor-arg name="name" value="波音747"/>
     * 　　　　　　　　</bean>
     *
     *      实例工厂：工厂本身需要创建对象
     *          工厂类 工厂对象 = new 工厂类();
     *          工厂对象.工厂方法名();
     *          区别就是 一个是静态的方法 一个不是 静态方法可以直接调用，不是静态需要先new　　
     *          使用方法　　　　　　
     *              1、先配置出实例工厂对象  <bean id="airPlaneInstaceFactory" class="com.lxy.factory.AirPlaneInstanceFactory"/>
     *              2、配置我们要创建的AirPlane使用哪个工厂创建
     *                  1)、factory-bean指定使用哪个工厂创建
     * 　　　　　　　　　　2)、factory-method指定使用哪个工厂方法　　　
     * 　　　　　　　　　　<bean id="airPlane02" class="com.lxy.pojo.AirPlane" factory-bean="airPlaneInstaceFactory" factory-method="getAirPlane">
     * 　　　　　　　　　　　　<constructor-arg name="name" value="波音787"/>
     * 　　　　　　　　　　</bean>
     *
     *          实现了FactoryBean的接口类是Spring可以认识的工厂类  自己写的工厂类需要继承FactoryBean
     *              Spring会自动的调用工厂方法创建实例
     *                  1、编写一个FactoryBean的实现类
     *                  2、在Spring配置文件中进行注册
     *                  <bean id="myFactoryBeanImpl" class="com.lxy.factory.MyFactoryBeanImpl"/>
     *                  tip: ioc容器启动时不会创建实例, 调用方法时根据是否为单例进行一次或多次创建
     */
    @Test
    public void text04() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring.xml");
        System.out.println(ioc.getBean("homework02"));
        System.out.println(ioc.getBean("homework03"));
        System.out.println(ioc.getBean("factoryBeanImpl"));
    }



    // 二、不同类型的赋值/注入方式

    /**
     * 1. 普通赋值
     *      <bean id="hello" class="com.lxy.pojo.Hello">
     *          <property name="str" value="Spring"/> 通过value
     *      </bean>
     * 2. 对象赋值
     *      <bean id="userServiceImpl" class="com.lxy.service.UserServiceImpl">
     *          <property name="userDao" ref="mysqlImpl"/>  通过ref
     *      <bean/>
     */
    @Test
    public void text05() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring02.xml");
        System.out.println(ioc.getBean("homework"));
        System.out.println(ioc.getBean("student"));
    }

    /**
     * 3. bean注入 (以下<bean id=""><bean>等省略，只写了注入方式)
     *      <property name="address" ref="hello"/>
     */
    @Test
    public void text06() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring03.xml");
        System.out.println(ioc.getBean("homework"));
        System.out.println(ioc.getBean(Student.class));
    }


    /**
     * 4. 数组注入　
     * 5. list 注入
     * 6. map注入
     * 7. null注入
     * 8. properties注入　
     * 9. 继承<bean>的配置信息
     */
    @Test
    public void text07() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring04.xml");
        System.out.println(ioc.getBean("homework"));
        System.out.println(ioc.getBean("student"));
        System.out.println(ioc.getBean("teacher"));
        System.out.println(ioc.getBean("director"));
    }


    // 三、通过命名空间注入值

    /**
     * 1)、p命名空间注入，可以直接注入属性的值: property
     * 　　singleton: 单例模式（spring默认机制）
     * 　　1、在容器启动完成之前就已经创建好对象，保存在容器中
     * 　　2、任何获取都是获取之前创建好的对象
     *
     * 　　导入p命名空间 xmlns:p="http://www.springframework.org/schema/p"
     *
     * 　　bean id="user" class="com.lxy.pojo.User" p:name="张三" p:age="24" scope="singleton"　
     *
     * 2)、c命名空间注入，通过构造器注入: constructor-args
     *
     * 　　prototype: 原型模式：每次从容器中get的时候，都会产生一个新的对象
     *
     * 　　1、容器启动默认不会去创建多实例bean　　
     * 　　2、获取的时候创建bean
     * 　　3、每次获取都会创建一个新的实例对象
     *
     * 　　导入p命名空间 xmlns:c="http://www.springframework.org/schema/c"
     *
     * 　　<bean id="user2" class="com.lxy.pojo.User" c:age="24" c:name="李四" scope="prototype"/>
     */

    @Test
    public void text08() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring05.xml");
        System.out.println(ioc.getBean("homework"));
        System.out.println(ioc.getBean("student"));
    }


    // 四、通过SpEL（Spring Expression Language）赋值　　

    /**
     * <bean id="user" class="com.lxy.pojo.User">
     *     字面量 #{} 可以在#{}里面直接写运算表达
     *     <property name="salary" value="#{12345.97*12}"/>
     *     引用其他bean的某个属性值
     *     <property name="studentName" value="#{student.name}" />
     *     引用其他bean
     *     <property name="book" value="#{book}"/>
     *     调用静态方法 #{T(全类名).方法名}
     *     <property name="id" value="#{T(java.util.UUID).randomUUID().toString().substring(0,5)}"/>
     *     调用非静态方法
     *     <property name="age" value="#{book.getPrice()}"/>
     * </bean>
     */

    @Test
    public void text09() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring06.xml");
        System.out.println(ioc.getBean("user"));
    }


    /**
     * 自动装配：仅限于对自定义类型的属性有效 前提是容器中必须有相对应的组件
     *      default: 不自动装配
     *      no: 不自动装配
     *      byName: 会自动在容器上下文中查找，和自己对象set方法后面的值对应的bean id  注意：不能够区分大小写，id都用小写
     *      byType: 会自动在容器上下文中查找，和自己对象属相同的bean （可以省略id）注意：需要保证类型全局唯一
     *      constructor: 按照有参构造器进行赋值
     *          1、先按照有参构造器参数的类型进行装配，没有装配null
     *          2、如果找到的类型有多个，参数的名作为id继续匹配
     *      如果属性是一个容器 例如list<book> 那么自动装配会把所有的book的bean装配进去
     *
     *      <bean id="people" class="com.lxy.pojo.People" autowire="byName">
     *          <property name="name" value="张三"/>
     *      </bean>
     */
    @Test
    public void text10() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring07.xml");
        System.out.println(ioc.getBean("student"));
    }


    /**
     * <context:component-scan base-package="com.web"/>: 开启注解并标注需要扫描的包
     *
     * @Component 组件，没有明确的角色
     *
     * @Service 在业务逻辑层使用（service层）
     *
     * @Repository 在数据访问层使用（dao层）
     *
     * @Controller 在展现层使用，控制器的声明（C）
     *
     * 每个组件都可以通过value设置一个名字
     *
     * @Autowired 注入bean的注解
     *
     * @Scope 设置Spring容器如何新建Bean实例（方法上，得有@Bean）
     *      Singleton （单例,一个Spring容器中只有一个bean实例，默认模式）
     *      Prototype （每次调用新建一个bean）
     *      Request （web项目中，给每个http request新建一个bean）
     *      Session （web项目中，给每个http session新建一个bean）
     *      GlobalSession（给每一个 global http session新建一个Bean实例）
     */
    @Test
    public void text11() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring08.xml");
        System.out.println(ioc.getBean(ControllerDemo.class));
        System.out.println(ioc.getBean("DaoDemo"));
        System.out.println(ioc.getBean(ServiceDemo.class));
    }


    /**
     * use-default-filters="false": 禁用默认扫描规则
     * <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
     * <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Service"/>
     * annotation：注解类型
     * assignable_type：指定的类型
     * aspectj：按照Aspectj的表达式，基本上不会用到
     * regex：按照正则表达式
     * custom：自定义规则
     */
    @Test
    public void text12() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring09.xml");
        System.out.println(ioc.getBean(ControllerDemo.class));
        System.out.println(ioc.getBean("DaoDemo"));
        // 下面这个没有 会报错的
        System.out.println(ioc.getBean(ServiceDemo.class));
    }


}
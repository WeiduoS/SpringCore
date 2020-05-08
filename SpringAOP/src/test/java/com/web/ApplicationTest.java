package com.web;

import com.web.manully.cglib.UserServiceImpl;
import com.web.manully.jdk.UserService;
import com.web.springaop.Human;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Weiduo
 * @date 2020/5/6 - 8:39 PM
 */
//@ContextConfiguration(locations = {"classpath:application.xml"})
//@ExtendWith(SpringExtension.class)
class ApplicationTest {

    @Autowired
    ApplicationContext ioc = null;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
     * 第一个参数handler.getClass().getClassLoader()，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
     * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
     * 第三个参数handler，我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
     */
    @Test
    public void test01() {

        final com.web.manully.jdk.UserService userService = new com.web.manully.jdk.UserServiceImpl();
        final com.web.manully.jdk.MyAspect myAspect = new com.web.manully.jdk.MyAspect();

        UserService proxy = (UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        myAspect.before();
                        Object obj = method.invoke(userService, args);
                        myAspect.after();

                        return obj;
                    }
                }
        );

        proxy.addUser();
        proxy.updateUser();
        proxy.deleteUser();
    }

    /**
     * CGLIB字节码增强
     * 1 目标类: final UserServiceImpl userService = new UserServiceImpl();
     * 2 切面类: final MyAspect myAspect = new MyAspect();
     * 3.代理类 ，采用cglib，底层创建目标类的子类
     *      3.1 核心类: Enhancer enhancer = new Enhancer();
     *      3.2 确定父类: enhancer.setSuperclass(userService.getClass());
     *      3.3 设置回调函数
     *          MethodInterceptor接口 == InvocationHandler接口
     *          intercept() ==  invoke() 参数1、参数2、参数3：和invoke一样 参数4：methodProxy 方法的代理
     */
    @Test
    public void test02() {

        final com.web.manully.cglib.UserServiceImpl userService = new com.web.manully.cglib.UserServiceImpl();
        final com.web.manully.cglib.MyAspect myAspect = new com.web.manully.cglib.MyAspect();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(userService.getClass());

        enhancer.setCallback(new MethodInterceptor(){
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                myAspect.before();

                Object obj = method.invoke(userService, args);
                methodProxy.invokeSuper(proxy, args);

                myAspect.after();
                return obj;
            }
        });

        UserServiceImpl proxService = (UserServiceImpl) enhancer.create();

        proxService.addUser();
        proxService.updateUser();
        proxService.deleteUser();
    }


    /**
     * <aop:advisor>的实现方式:
     * 定义<aop:advisor>中引用的通知时，通知必须实现Advice接口。
     */
    @Test
    public void test03() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("application.xml");
        Human human = (Human) ioc.getBean("human");
        human.sleep();

    }

    /**
     * <aop:aspect>的实现方式:
     * <aop:aspect>定义切面时，只需要定义一般的bean就行
     */
    @Test
    public void test04() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("application02.xml");
        Human human = (Human) ioc.getBean("human");
        human.sleep();

    }

    /**
     * <context:component-scan base-package="com.itheima.d_aspect.b_anno"></context:component-scan>
     * <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
     */
    @Test
    public void test05() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("application03.xml");
        com.web.springaop.annotation.UserServiceImpl userService = ioc.getBean(com.web.springaop.annotation.UserServiceImpl.class);
        userService.addUser();
        userService.updateUser();
        userService.deleteUser();

    }

}
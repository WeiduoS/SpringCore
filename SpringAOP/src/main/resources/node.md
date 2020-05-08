# _**Spring AOP**_

## 1、什么是aop：
- AOP（Aspect Oriented Programming）称为面向切面编程，在程序开发中主要用来解决一些系统层面上的问题，比如日志，事务，权限等待，Struts2的拦截器设计就是基于AOP的思想，是个比较经典的例子。
- 在不改变原有的逻辑的基础上，增加一些额外的功能。代理也是这个功能，读写分离也能用aop来做。
- AOP可以说是OOP（Object Oriented Programming，面向对象编程）的补充和完善。OOP引入封装、继承、多态等概念来建立一种对象层次结构，用于模拟公共行为的一个集合。不过OOP允许开发者定义纵向的关系，但并不适合定义横向的关系，例如日志功能。日志代码往往横向地散布在所有对象层次中，而与它对应的对象的核心功能毫无关系对于其他类型的代码，如安全性、异常处理和透明的持续性也都是如此，这种散布在各处的无关的代码被称为横切（cross cutting），在OOP设计中，它导致了大量代码的重复，而不利于各个模块的重用。
- AOP技术恰恰相反，它利用一种称为"横切"的技术，剖解开封装的对象内部，并将那些影响了多个类的公共行为封装到一个可重用模块，并将其命名为"Aspect"，即切面。所谓"切面"，简单说就是那些与业务无关，却为业务模块所共同调用的逻辑或责任封装起来，便于减少系统的重复代码，降低模块之间的耦合度，并有利于未来的可操作性和可维护性。
- 使用"横切"技术，AOP把软件系统分为两个部分：核心关注点和横切关注点。业务处理的主要流程是核心关注点，与之关系不大的部分是横切关注点。横切关注点的一个特点是，他们经常发生在核心关注点的多处，而各处基本相似，比如权限认证、日志、事物。AOP的作用在于分离系统中的各种关注点，将核心关注点和横切关注点分离开来。

## 2、AOP的相关概念：
- 横切关注点：对哪些方法进行拦截，拦截后怎么处理，这些关注点称之为横切关注点
- Aspect(切面):通常是一个类，里面可以定义切入点和通知
- JointPoint(连接点):程序执行过程中明确的点，一般是方法的调用。被拦截到的点，因为Spring只支持方法类型的连接点，所以在Spring中连接点指的就是被拦截到的方法，实际上连接点还可以是字段或者构造器
- Advice(通知):AOP在特定的切入点上执行的增强处理，有before(前置),after(后置),afterReturning(最终),afterThrowing(异常),around(环绕)
- Pointcut(切入点):就是带有通知的连接点，在程序中主要体现为书写切入点表达式
- weave(织入)：将切面应用到目标对象并导致代理对象创建的过程
- introduction(引入)：在不修改代码的前提下，引入可以在运行期为类动态地添加一些方法或字段
- AOP代理(AOP Proxy)：AOP框架创建的对象，代理就是目标对象的加强。Spring中的AOP代理可以使JDK动态代理，也可以是CGLIB代理，前者基于接口，后者基于子类
- 目标对象（Target Object）: 包含连接点的对象。也被称作被通知或被代理对象。POJO
## 3、实现方式
###  使用JDK动态代理
Interface
```java
public interface UserService {
    void addUser();
    void updateUser();
    void deleteUser();
}
```
target
```java
public class UserServiceImpl implements UserService {

    @Override
    public void addUser() {
        System.out.println("a_proxy a_jdk add user ... ");

    }

    @Override
    public void updateUser() {
        System.out.println("a_proxy a_jdk update user ... ");

    }

    @Override
    public void deleteUser() {
        System.out.println("a_proxy a_jdk delete user ... ");

    }

}
```
Aspect

```java
public class MyAspect {
    public void before(){
        System.out.println("jdk_proxy之目标方法执行前 ");
    }
    
    public void after(){
        System.out.println("jdk_proxy之目标方法执行后");
    }
    
}
```

### 使用CGLIB字节码增强

target
```java
public class UserServiceImpl{

    public void addUser() {
        System.out.println("a_proxy b_cglib add user ... ");

    }

    public void updateUser() {
        System.out.println("a_proxy b_cglib update user ... ");

    }

    public void deleteUser() {
        System.out.println("a_proxy b_cglib delete user ... ");

    }

}
```

Aspect
```java
public class MyAspect {
    public void before(){
        System.out.println("cglib之目标方法执行前");
    }
    
    public void after(){
        System.out.println("cglib之目标方法执行后 ");
    }
    
}
```

### AspectJ通知类型
```
before:前置通知(应用：各种校验)
    在方法执行前执行，如果通知抛出异常，阻止方法运行
afterReturning:后置通知(应用：常规数据处理)
    方法正常返回后执行，如果方法中抛出异常，通知无法执行
    必须在方法执行后才执行，所以可以获得方法的返回值。
around:环绕通知(应用：十分强大，可以做任何事情)
    方法执行前后分别执行，可以阻止方法的执行
    必须手动执行目标方法
afterThrowing:抛出异常通知(应用：包装异常信息)
    方法抛出异常后执行，如果方法没有抛出异常，无法执行
after:最终通知(应用：清理现场)
    方法执行完毕后执行，无论方法中是否出现异常
```


![ApplicationContext](images/img01.png)


## Relative Link
- [spring aop 及实现方式](https://www.jianshu.com/p/5b9a0d77f95f)
- [Java - 动态代理机制讲解（Proxy.newProxyInstance）](https://blog.csdn.net/Dream_Weave/article/details/84183247)
- [CGLib动态代理](https://www.cnblogs.com/wyq1995/p/10945034.html)
- [Spring AOP中pointcut expression表达式解析](https://www.jianshu.com/p/5249d4532761)
- [<aop:aspect>与<aop:advisor>的区别](https://www.jianshu.com/p/40f79da0cdef)

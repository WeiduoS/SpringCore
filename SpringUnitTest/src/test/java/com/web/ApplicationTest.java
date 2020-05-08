package com.web;

import com.web.beans.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Weiduo
 * @date 2020/5/6 - 6:51 PM
 */

@ContextConfiguration(locations = {"classpath:application.xml"})
//RunWith的value属性指定以spring test的SpringJUnit4ClassRunner作为启动类
//这里用的升级版本是Spring 5 + JUnit 5
//@RunWith(value = SpringJUnit4ClassRunner.class)
//In JUnit 5, the @RunWith annotation has been replaced by the more powerful @ExtendWith annotation.
@ExtendWith(SpringExtension.class)
class ApplicationTest {


    ApplicationContext ioc = null;

    @Autowired
    Student student;

    @BeforeEach
    void setUp() {
        System.out.println(ioc == null);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void test01() {
        assertTrue("小明".equals(student.getName()));
    }
}
package com.web;

import com.web.service.FacultyService;
import com.web.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Weiduo
 * @date 2020/5/6 - 7:29 PM
 */
@ContextConfiguration(locations = {"classpath:application.xml"})
@ExtendWith(SpringExtension.class)
class ApplicationTest {

    @Autowired
    ApplicationContext ioc = null;

    @BeforeEach
    void setUp() {
        System.out.println(ioc == null);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * 这里的printType是java内置的反射机制提供的方法
     * 它可以从.class的二进制文件中读取相应的类信息
     */

    @Test
    public void test01() {
        FacultyService facultyService = ioc.getBean(FacultyService.class);
        StudentService studentService = ioc.getBean(StudentService.class);

        facultyService.save();
        studentService.save();

        facultyService.printType();
        studentService.printType();

    }

}
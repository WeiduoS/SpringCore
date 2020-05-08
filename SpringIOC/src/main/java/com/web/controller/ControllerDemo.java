package com.web.controller;

import com.web.service.ServiceDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;

/**
 * @author Weiduo
 * @date 2020/5/6 - 11:05 AM
 */
@Controller(value = "ControllerDemo")
public class ControllerDemo {

    @Autowired
    @Nullable
    ServiceDemo service;

    String msg;

    public ControllerDemo() {
        msg = "Controller Demo";
    }

    @Override
    public String toString() {
        return "ControllerDemo{} " + this.service;
    }
}

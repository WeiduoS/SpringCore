package com.web.service;

import com.web.dao.DaoDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 * @author Weiduo
 * @date 2020/5/6 - 11:05 AM
 */
@Service(value = "ServiceDemo")
public class ServiceDemo {

    @Autowired
    @Nullable
    DaoDemo dao;

    String msg;

    public ServiceDemo() {
        msg = "Service Demo";
    }

    @Override
    public String toString() {
        return "ServiceDemo{} " + this.dao;
    }
}

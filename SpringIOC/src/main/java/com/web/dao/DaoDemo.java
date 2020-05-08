package com.web.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Weiduo
 * @date 2020/5/6 - 11:05 AM
 */
@Repository(value = "DaoDemo")
public class DaoDemo {

    String msg;

    public DaoDemo() {
        msg = "Dao Demo";
    }

    @Override
    public String toString() {
        return "DaoDemo{}";
    }
}

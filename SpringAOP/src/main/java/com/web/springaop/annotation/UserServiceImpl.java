package com.web.springaop.annotation;

import org.springframework.stereotype.Service;

/**
 * @author Weiduo
 * @date 2020/5/7 - 2:27 AM
 */
@Service
public class UserServiceImpl {
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

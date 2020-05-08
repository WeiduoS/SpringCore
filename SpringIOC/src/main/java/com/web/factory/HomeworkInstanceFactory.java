package com.web.factory;

import com.web.beans.Homework;

/**
 * @author Weiduo
 * @date 2020/5/5 - 2:17 AM
 */
public class HomeworkInstanceFactory {
    public Homework homework(String content) {
        return new Homework(content);
    }
}

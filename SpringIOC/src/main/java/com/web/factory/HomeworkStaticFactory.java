package com.web.factory;

import com.web.beans.Homework;

/**
 * @author Weiduo
 * @date 2020/5/5 - 2:16 AM
 */
public class HomeworkStaticFactory {

    public static Homework homework(String content) {
        return new Homework(content);
    }
}

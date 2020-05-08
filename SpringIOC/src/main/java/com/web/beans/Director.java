package com.web.beans;

/**
 * @author Weiduo
 * @date 2020/5/5 - 12:39 AM
 */
public class Director extends Teacher {
    private String title;

    public Director() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return super.toString() + "Director{" +
                "title='" + title + '\'' +
                '}';
    }
}

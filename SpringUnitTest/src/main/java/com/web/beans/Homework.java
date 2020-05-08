package com.web.beans;

/**
 * @author Weiduo
 * @date 2020/5/4 - 12:03 AM
 */
public class Homework {

    private String content;

    public Homework(){}
    public Homework(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "content='" + content + '\'' +
                '}';
    }
}

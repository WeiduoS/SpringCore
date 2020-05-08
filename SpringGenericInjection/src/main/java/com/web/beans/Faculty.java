package com.web.beans;

/**
 * @author Weiduo
 * @date 2020/5/6 - 7:23 PM
 */
public class Faculty {
    private int id;
    private String name;
    private String evaluation;

    public Faculty(int id, String name, String evaluation) {
        this.id = id;
        this.name = name;
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        return "Faculty [id=" + id + ", name=" + name + ", evaluation=" + evaluation + "]";
    }

}

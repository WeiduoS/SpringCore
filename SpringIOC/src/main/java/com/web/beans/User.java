package com.web.beans;

/**
 * @author Weiduo
 * @date 2020/5/5 - 12:50 AM
 */
public class User {

    private Double salary;
    private String studentName;
    private Homework homework;
    private String id;
    private String content;


    public User() {
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "User{" +
                "salary=" + salary +
                ", studentName='" + studentName + '\'' +
                ", homework=" + homework +
                ", id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}

package com.web.beans;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Weiduo
 * @date 2020/5/4 - 11:54 PM
 */
public class Teacher {

    private String[] books;
    private List<String> hobbies;
    private List<Student> students;
    private Map<Integer, String> card;
    private Map<Integer, String> map;
    private String driveLicence;
    private Properties info;


    public Teacher() {
    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Map<Integer, String> getCard() {
        return card;
    }

    public void setCard(Map<Integer, String> card) {
        this.card = card;
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }

    public String getDriveLicence() {
        return driveLicence;
    }

    public void setDriveLicence(String driveLicence) {
        this.driveLicence = driveLicence;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "books=" + Arrays.toString(books) +
                ", hobbies=" + hobbies +
                ", students=" + students +
                ", card=" + card +
                ", map=" + map +
                ", driveLicence='" + driveLicence + '\'' +
                ", info=" + info +
                '}';
    }
}

package com.web.dao;

import com.web.beans.Student;
import org.springframework.stereotype.Repository;

/**
 * @author Weiduo
 * @date 2020/5/6 - 7:23 PM
 */
@Repository
public class StudentRepository extends BaseRepository<Student>{
    public void save() {
        System.out.println("保存Student ");
    }
}

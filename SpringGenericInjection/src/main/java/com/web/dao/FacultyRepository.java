package com.web.dao;

import com.web.beans.Faculty;
import org.springframework.stereotype.Repository;

/**
 * @author Weiduo
 * @date 2020/5/6 - 7:24 PM
 */
@Repository
public class FacultyRepository extends BaseRepository<Faculty>{
    public void save() {
        System.out.println("保存Faculty");
    }
}

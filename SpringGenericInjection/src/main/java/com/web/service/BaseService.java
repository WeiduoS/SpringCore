package com.web.service;

import com.web.dao.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Weiduo
 * @date 2020/5/6 - 7:39 PM
 */
public class BaseService<T> {

    @Autowired
    BaseRepository<T> baseRepository;

    public void save() {
        baseRepository.save();
    }

    public void printType() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        Class<T> entityClass = (Class<T>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        System.out.println(entityClass);
    }
}

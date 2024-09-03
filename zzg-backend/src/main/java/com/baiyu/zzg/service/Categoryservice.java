package com.baiyu.zzg.service;

import com.baiyu.zzg.pojo.Category;

import java.util.List;

public interface Categoryservice {
    void add(Category category);

    List<Category> list();

    Category findById(Integer id);

    void update(Category category);

    void deleteById(Integer id);
}

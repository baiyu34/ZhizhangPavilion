package com.itheima.bigevent.service.impl;

import com.itheima.bigevent.mapper.Categorymapper;
import com.itheima.bigevent.pojo.Category;
import com.itheima.bigevent.service.Categoryservice;
import com.itheima.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class Categoryserviceimpl implements Categoryservice {

    @Autowired
    private Categorymapper categorymapper;

    @Override
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);
        categorymapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return categorymapper.list(userId);
    }

    @Override
    public Category findById(Integer id) {
        Category c = categorymapper.findById(id);
        return c;
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categorymapper.update(category);
    }

    @Override
    public void deleteById(Integer id) {
        categorymapper.deleteById(id);
    }
}

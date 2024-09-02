package com.itheima.bigevent.service;

import com.itheima.bigevent.pojo.Article;
import com.itheima.bigevent.pojo.Category;
import com.itheima.bigevent.pojo.PageBean;

public interface Articleservice {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);


    void update(Article article);

    void deleteById(Integer id);
}

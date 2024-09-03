package com.baiyu.zzg.service;

import com.baiyu.zzg.pojo.Article;
import com.baiyu.zzg.pojo.PageBean;

public interface Articleservice {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);


    void update(Article article);

    void deleteById(Integer id);
}

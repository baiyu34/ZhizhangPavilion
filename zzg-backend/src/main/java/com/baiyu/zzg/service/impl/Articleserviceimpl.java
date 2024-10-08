package com.baiyu.zzg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.baiyu.zzg.mapper.Articlemapper;
import com.baiyu.zzg.pojo.Article;
import com.baiyu.zzg.pojo.PageBean;
import com.baiyu.zzg.service.Articleservice;
import com.baiyu.zzg.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class Articleserviceimpl implements Articleservice {

    @Autowired
    private Articlemapper articlemapper;

    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        articlemapper.add(article);
    }



    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pb = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articlemapper.list(userId,categoryId,state);
        Page<Article> p = (Page<Article>) as;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articlemapper.update(article);
    }

    @Override
    public void deleteById(Integer id) {
        articlemapper.deleteById(id);
    }
}

package com.baiyu.zzg.controller;

import com.baiyu.zzg.pojo.Article;
import com.baiyu.zzg.pojo.Category;
import com.baiyu.zzg.pojo.PageBean;
import com.baiyu.zzg.pojo.Result;
import com.baiyu.zzg.service.Articleservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class Articlecontroller {

    @Autowired
    private Articleservice articleservice;

    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleservice.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb =  articleservice.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }

    @DeleteMapping("/delete")
    public Result<Category> delete(Integer id){
        articleservice.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated Article article){
        articleservice.update(article);
        return Result.success();
    }
}

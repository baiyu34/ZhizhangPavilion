package com.baiyu.zzg.controller;

import com.baiyu.zzg.pojo.Category;
import com.baiyu.zzg.pojo.Result;
import com.baiyu.zzg.service.Categoryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")

public class Categorycontroller {

    @Autowired
    private Categoryservice categoryservice;

    @PostMapping
    public Result add(@RequestBody @Validated Category category){
        categoryservice.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list(){
        List<Category> cs = categoryservice.list();
        return Result.success(cs);
    }

    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        Category c = categoryservice.findById(id);
        return Result.success(c);
    }

    @DeleteMapping
    public Result<Category> delete(Integer id){
        categoryservice.deleteById(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryservice.update(category);
        return Result.success();
    }
}

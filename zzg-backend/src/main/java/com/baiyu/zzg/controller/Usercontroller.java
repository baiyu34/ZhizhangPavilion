package com.baiyu.zzg.controller;

import com.baiyu.zzg.pojo.Result;
import com.baiyu.zzg.pojo.User;
import com.baiyu.zzg.service.Userservice;
import com.baiyu.zzg.utils.JwtUtil;
import com.baiyu.zzg.utils.Md5Util;
import com.baiyu.zzg.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class Usercontroller {
    @Autowired
    private Userservice userservice;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$")String username,@Pattern(regexp = "^\\S{5,16}$") String password) {

            User u = userservice.findByUserName(username);
            if (u == null) {
                userservice.register(username, password);
                return Result.success();
            } else {
                return Result.error("用户名已被占用");
            }

        }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$")String username,@Pattern(regexp = "^\\S{5,16}$") String password) {
        User loginUser = userservice.findByUserName(username);

        if (loginUser == null) {
            return Result.error("用户名错误");
        }

        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            Map<String,Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userinfo")
    public Result<User> userInfo(/*@RequestHeader name = "Authorization") String token*/){
//        Map<String,Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userservice.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userservice.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userservice.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        String old_pwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String re_pwd = params.get("re_pwd");
        if(!StringUtils.hasLength(old_pwd)||!StringUtils.hasLength(new_pwd)||!StringUtils.hasLength(re_pwd)) {
            return Result.error("参数错误");
        }

        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userservice.findByUserName(username);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(old_pwd))){
            return Result.error("原密码错误");
        }

        if(!re_pwd.equals(new_pwd)){
            return Result.error("两次密码填写不一致");
        }

        userservice.updatePwd(new_pwd);
        return Result.success();
    }

}
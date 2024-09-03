package com.baiyu.zzg.service;


import com.baiyu.zzg.pojo.User;

public interface Userservice {

    User findByUserName(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String new_pwd);
}

package com.baiyu.zzg.service.impl;


import com.baiyu.zzg.mapper.Usermapper;
import com.baiyu.zzg.pojo.User;
import com.baiyu.zzg.service.Userservice;
import com.baiyu.zzg.utils.Md5Util;
import com.baiyu.zzg.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class Userserviceimpl implements Userservice {
    @Autowired
    private Usermapper usermapper;

    @Override
    public User findByUserName(String username) {
        User u = usermapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        usermapper.add(username,md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        usermapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        usermapper.updateAvatar(avatarUrl,id);

    }

    @Override
    public void updatePwd(String new_pwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        usermapper.updatePwd(Md5Util.getMD5String(new_pwd),id);
    }
}


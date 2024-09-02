package com.itheima.bigevent.service.impl;


import com.itheima.bigevent.mapper.Usermapper;
import com.itheima.bigevent.pojo.User;
import com.itheima.bigevent.service.Userservice;
import com.itheima.bigevent.utils.Md5Util;
import com.itheima.bigevent.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
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


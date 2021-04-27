package com.xm.qimanwang.services;

import com.xm.qimanwang.entity.Users;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface LoginService {
    //判断用户登录判断
    public Map<String,Boolean> isPhone(String phone, String password);
    //用户注册
    public Map<String,Boolean> register(Users users);

}

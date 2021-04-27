package com.xm.qimanwang.services.impl;

import com.xm.qimanwang.entity.Users;
import com.xm.qimanwang.mapper.UsersMapper;
import com.xm.qimanwang.services.LoginService;
import com.xm.qimanwang.util.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UsersMapper usersMapper;


    @Override
    public Map<String,Boolean> isPhone(String phone, String password) {
        Map<String,Boolean> map = new HashMap<>();
        Users u; //用户对象
        u = usersMapper.getUserByPhone(phone);
        if (u!=null){
            map.put("checkPhone",true);
            //判断密码是否正确
            boolean equalsToMd5 = MD5Utils.isEqualsToMd5(password, u.getPassword());
            if (equalsToMd5){
                map.put("checkPassword",true);
            }else{
                map.put("checkPassword",false);
            }

        }else{
            map.put("checkPhone",false);
        }
        return map;
    }

    @Override
    public Map<String, Boolean> register(Users users) {
        Map<String,Boolean> msg = new HashMap<>();
        //先判断手机号是否存在
        if (usersMapper.getUserByPhone(users.getPhone())==null){
            System.out.println("手机号不存在");
//            插入新用户信息
            usersMapper.inNewUser(users);
            msg.put("isPhone",true);
        }else{
            System.out.println("手机存在");
            msg.put("isPhone",false);
        }
        return msg;
    }


}

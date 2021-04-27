package com.xm.qimanwang.mapper;

import com.xm.qimanwang.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {
    //获取所有用户
    public List<Users> getUsersAll();
    //根据手机号获取记录
    public Users getUserByPhone(String phone);
    //插入一条用户信息
    public void inNewUser(Users users);

}
package com.xm.qimanwang.services;

public interface LikesService {

    //增加对应文章的like数，每个用户只能点赞一次，也能取消
    public String like(String phone,Long articleID);

}

package com.xm.qimanwang.mapper;

import com.xm.qimanwang.entity.Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LikeMapper {

    //查询所有数据
    public List<Like> getLike();

    //通过文章id和用户id去查询用户的点赞记录
    public Like getLikeByUserIdAndArticleId( String phone, Long articleID);

    //插入一条数据
    public void inLike(Like like);

    //修改是否点赞
    public void updateLike(Like like);

}
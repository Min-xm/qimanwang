package com.xm.qimanwang.mapper;

import com.xm.qimanwang.entity.CommentOne;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentOneMapper {
    //返回对应文章ID的所有一级评论
    public List<CommentOne> getCommentOneById(Long articleid);
}
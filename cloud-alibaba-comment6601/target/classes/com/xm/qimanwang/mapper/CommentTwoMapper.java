package com.xm.qimanwang.mapper;


import com.xm.qimanwang.entity.CommentOne;
import com.xm.qimanwang.entity.CommentTwo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CommentTwoMapper {

    //返回对应文章的一级评论
    public List<CommentTwo> getCommentOneById(Long articleid);
    //返回对应文章的二级评论
    public List<CommentTwo> getCommentTwoById(Long articleid);
    //发表一级评论
    public void addCommentOne(CommentTwo commentTwo);
    //发表二级评论
    public void addCommentTwo(CommentTwo commentTwo);

}
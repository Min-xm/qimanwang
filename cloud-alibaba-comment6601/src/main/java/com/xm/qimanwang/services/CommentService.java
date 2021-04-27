package com.xm.qimanwang.services;

import com.xm.qimanwang.entity.CommentTwo;

import java.util.List;
import java.util.Map;

public interface CommentService {
    //通过文章id获取相应的评论
    public Map<String, List<CommentTwo>> getCommentById(Long articleid);
    //发表一，二级评论
    public String addComment(CommentTwo commentTwo);

}

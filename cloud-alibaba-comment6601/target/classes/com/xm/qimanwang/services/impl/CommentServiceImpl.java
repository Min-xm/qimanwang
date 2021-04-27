package com.xm.qimanwang.services.impl;

import com.xm.qimanwang.entity.CommentOne;
import com.xm.qimanwang.entity.CommentTwo;
import com.xm.qimanwang.mapper.CommentOneMapper;
import com.xm.qimanwang.mapper.CommentTwoMapper;
import com.xm.qimanwang.services.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    public CommentTwoMapper commentTwoMapper;


    @Override
    public Map<String, List<CommentTwo>> getCommentById(Long articleid) {
        Map<String,List<CommentTwo>> map = new HashMap<>();

        List<CommentTwo> commentOneList = commentTwoMapper.getCommentOneById(articleid);
        List<CommentTwo> commentTwoList = commentTwoMapper.getCommentTwoById(articleid);

        map.put("commentOne",commentOneList);
        map.put("commentTwo", commentTwoList);

        return map;
    }

    @Override
    public String addComment(CommentTwo commentTwo){
        //获取系统时间,设置发布日期
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        commentTwo.setCommentdate(sdf.format(date));
        if(commentTwo.getRespondentid()==null){
            commentTwoMapper.addCommentOne(commentTwo);
        }else {
            commentTwoMapper.addCommentTwo(commentTwo);
        }
        return "评论发表成功";
    }
}

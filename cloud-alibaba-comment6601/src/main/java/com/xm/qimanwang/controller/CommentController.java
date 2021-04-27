package com.xm.qimanwang.controller;

import com.xm.qimanwang.entity.CommentOne;
import com.xm.qimanwang.entity.CommentTwo;
import com.xm.qimanwang.mapper.CommentOneMapper;
import com.xm.qimanwang.mapper.CommentTwoMapper;
import com.xm.qimanwang.services.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {

    @Resource
    public CommentService commentService;

    @GetMapping("/test/{articleid}")
    @ResponseBody
    public Object test(@PathVariable String articleid){
        Long id = Long.parseLong(articleid);
        Map<String,List<CommentTwo>> map = commentService.getCommentById(id);
        return map;
    }

    //发表评论
    @PostMapping("/comment")
    @ResponseBody
    public Object addComment(CommentTwo commentTwo){
        System.out.println(commentTwo.toString());
        return commentService.addComment(commentTwo);
    }



}

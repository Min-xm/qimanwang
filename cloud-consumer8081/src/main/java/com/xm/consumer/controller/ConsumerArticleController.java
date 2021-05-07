package com.xm.consumer.controller;

import com.xm.consumer.services.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ConsumerArticleController {

    @Resource
    public ArticleService articleService;


    @GetMapping("/article")
    @ResponseBody
    public Object getArticleAll(){
        System.out.println("测试热部署更新");
        return articleService.getArticle();
    }



}

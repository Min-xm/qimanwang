package com.xm.qimanwang.controller;

import com.xm.qimanwang.entity.Article;
import com.xm.qimanwang.mapper.ArticleMapper;
import com.xm.qimanwang.services.ArticleService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RefreshScope   //Nacos的动态刷新功能
public class PublishController {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    ArticleService articleServiceImp;


    @GetMapping("/test")
    @ResponseBody
    public Object test(HttpSession session){
        System.out.println(session.getAttribute("MyUser"));
        return "测试";
    }


    @GetMapping("/publish")
    public String goPublishArticle(){
        return "/blog";
    }

    @GetMapping("/upArticle")
    public String goArticle(){
        return "/upblog";
    }

    @GetMapping("/angular")
    public String goAngular(){
        return "/angular";
    }

    @GetMapping("/article")
    public String goArticles(){
        return "/article";
    }

    @GetMapping("/home")
    public String goHome(){
        return "/home";
    }

    @GetMapping("/Information")
    public String goInformation(){
        return "/Information";
    }

    @GetMapping("/game")
    public String goGame(){
        return "/game";
    }

    @GetMapping("/sound")
    public String goSound(){
        return "/sound";
    }

    @GetMapping("/getArticle")
    @ResponseBody
    public List<Article> getArticle(){
        return articleMapper.getArticleAll();
    }

    @GetMapping("/getArticleAll")
    @ResponseBody
    public List<Article> getArticleAll(){
        return articleServiceImp.getArticleAll();
    }

    //根据对应的文章id跳转到相应文章页面
    @GetMapping("/article/{articleid}")
    public String goArticleById(@PathVariable String articleid, Model model,HttpSession session){
        long id = Long.parseLong(articleid);
        Article article = articleServiceImp.getArticleById(id);
        model.addAttribute("article",article);
        System.out.println(article.toString());
        return "/article";
    }

    //根据对应的文章id跳转到相应的修改页面
    @GetMapping("/updateArticle/{articleid}")
    public String updateArticle(@PathVariable String articleid, Model model){
        long id = Long.parseLong(articleid);
        Article article = articleServiceImp.getArticleById(id);
        model.addAttribute("article",article);
        return "/upblog";
    }
    //保存文章修改
    @GetMapping("/article/saveArticle")
    public String saveArticle(Article article){
        articleServiceImp.saveArticle(article);
        return "/angular";
    }

    //添加文章接口
    @RequestMapping("/article/addArticle")
    public String addArticle(Article article){
//        Map<String,Object> map = new HashMap<>();
        if (articleServiceImp.addArticle(article)){
            return "/angular";
        }
        return "/blog";
    }

    //删除对应文章id的文章
    @GetMapping("/article/deleteArticle/{articleid}")
    public String deleteArticle(@PathVariable String articleid){
        long id = Long.parseLong(articleid);
        System.out.println(articleServiceImp.deleteArticle(id));
        return "/angular";
    }

    @GetMapping("/article/getArticleByTags")
    @ResponseBody
    //分类获取不同标签的文章信息
    public Map<String,List<Article>> getArticleAllByTags(){
        Map<String, List<Article>> map = articleServiceImp.getArticleByTags("");
        return map;
    }

    @GetMapping("/article/getArticleByTags/{tags}")
    @ResponseBody
    //分类获取不同标签的文章信息
    public Map<String,List<Article>> getArticleAllByTags(@PathVariable String tags){
        Map<String, List<Article>> map = articleServiceImp.getArticleByTags(tags);
        return map;
    }

}

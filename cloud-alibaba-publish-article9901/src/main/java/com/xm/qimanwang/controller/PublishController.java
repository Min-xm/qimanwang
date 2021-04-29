package com.xm.qimanwang.controller;

import com.xm.qimanwang.entity.Article;
import com.xm.qimanwang.mapper.ArticleMapper;
import com.xm.qimanwang.services.ArticleService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    public Object test(){
       List<Article> articles = articleMapper.selectArticleByTileAndTagsAndContent("猫","资讯","");
        return articles;
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

    @GetMapping("/article/search")
    @ResponseBody
    //查询文章数据
    public Map<String,Object> searchArticle(Article article){
        Map<String,Object> map = new HashMap<>();
        //索引标题
        String title = article.getTitle();
        //索引标签
        String  tags = article.getTags();
        //索引内容
        String content = article.getContent();
        //保存索引对象
        List<Article> articles;
        if (title!=null&&!title.isEmpty() || content!=null&&!content.isEmpty()){
            articles = articleServiceImp.searchArticle(article);
            map.put("msg","模糊查询成功");
            map.put("code","200");
            map.put("article",articles);
        }else if (tags==null||title.isEmpty() && content==null||content.isEmpty()){
            articles = articleServiceImp.searchArticle(article);
            map.put("msg","按标题查询");
            map.put("code","200");
            map.put("article",articles);
        } else {
            map.put("msg","没有数据返回");
            map.put("code","400");
            map.put("article","{}");
        }
        return map;
    }


    //文件上传
    @PostMapping("/upload")
    @ResponseBody
    public String UploadFiles(@RequestParam("file")MultipartFile file){
        if (file.isEmpty()) {
            System.out.println("上传失败，请选择文件");
            return "上传失败，请选择文件";
        }
        System.out.println("上传文件成功");
        return "上传文件成功";
    }

}

package com.xm.qimanwang.services.impl;

import com.xm.qimanwang.entity.Article;
import com.xm.qimanwang.mapper.ArticleMapper;
import com.xm.qimanwang.services.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;


    @Override
    public Boolean addArticle(Article article) {
        //如果添加文章成功返true
        try{
            //自动生成文章ID号
            Random random = new Random();
            Long AID = random.nextLong();
            //while循环防止AID取 2负64次方~0的值
            while(AID<=0||AID>9999999999999999L){
                AID = random.nextLong();
            }
            //自动生成id（缺陷有极小概率ID重复（大概是百亿万亿分之几的概率吧！哈哈哈哈））
            article.setArticleid(AID);
            //获取系统时间,设置发布日期
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            article.setPublishdate(sdf.format(date));
            article.setUpdatedate(sdf.format(date));

            //添加文章
            articleMapper.inArticle(article);
            System.out.println(article.toString());
            return true;
        }catch (Exception e){
            System.out.println("文章添加失败");
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Article> getArticleAll() {
        return articleMapper.getArticleAll();
    }

    @Override
    public Article getArticleById(Long articleid) {
        Article articleById = articleMapper.getArticleById(articleid);
        return articleById;
    }

    @Override
    public void saveArticle(Article article) {
        //获取系统时间,设置发布日期
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setUpdatedate(sdf.format(date));
        articleMapper.updateArticle(article);
    }

    @Override
    public String deleteArticle(Long articleid) {
        try{
            articleMapper.deleteArticleById(articleid);
            return "成功删除信息";
        }catch (Exception e){
            e.printStackTrace();
            return "删除失败";
        }
    }

    @Override
    public Map<String, List<Article>> getArticleByTags(String tags) {
        Map<String,List<Article>> map = new HashMap<>();
        if (tags.isEmpty()){
            List<Article> list1 = articleMapper.selectArticleByTags("音声");
            List<Article> list2 = articleMapper.selectArticleByTags("图集");
            List<Article> list3 = articleMapper.selectArticleByTags("资讯");
            List<Article> list4 = articleMapper.selectArticleByTags("游戏");
            List<Article> list5 = articleMapper.selectArticleByTags("漫画轻小说");
            map.put("音声",list1);
            map.put("图集",list2);
            map.put("资讯",list3);
            map.put("游戏",list4);
            map.put("漫画轻小说",list5);
            return map;
        }else{
            List<Article> list = articleMapper.selectArticleByTags(tags);
            map.put(tags,list);
            return map;
        }
    }

    @Override
    public List<Article> searchArticle(Article article) {
        List<Article> list;
        if (article.getContent()==null){
            list = articleMapper.selectArticleByTileAndTagsAndContent(article.getTitle(),article.getTags(),null);
        }else if(article.getTitle()==null){
            list = articleMapper.selectArticleByTileAndTagsAndContent(null,article.getTags(),article.getContent());
        }else if(article.getTitle()==null && article.getContent()==null){
            list = articleMapper.selectArticleByTileAndTagsAndContent(null,article.getTags(),null);
        }else{
            list = articleMapper.selectArticleByTileAndTagsAndContent(article.getTitle(),article.getTags(),article.getContent());
        }
        return list;
    }
}

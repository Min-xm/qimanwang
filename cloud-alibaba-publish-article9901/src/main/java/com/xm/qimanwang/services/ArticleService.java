package com.xm.qimanwang.services;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.xm.qimanwang.entity.Article;

import java.util.List;
import java.util.Map;


public interface ArticleService {
    //添加一条文章记录
    Boolean addArticle(Article article);

    //返回所有记录（测试）
    List<Article> getArticleAll();

    //根据文章id查询对应的文章
    @Cached(name = "ArticleService.getArticleById",key = "#articleid",expire = 3600)
    Article getArticleById(Long articleid);

    //保存文章信息
    @CacheUpdate(name="ArticleService.getArticleById",key = "#article.articleid",value = "#article")
    void saveArticle(Article article);

    //删除对应的文章
    @CacheInvalidate(name="ArticleService.getArticleById",key = "#articleid")
    String deleteArticle(Long articleid);

    //分类获取不同标签的文章信息
    @Cached(name = "ArticleService.getArticleByTags",expire = 3600)
    Map<String, List<Article>> getArticleByTags(String tags);

    //通过文章标题、文章标签查询和内容查询
    List<Article> searchArticle(Article article);


}

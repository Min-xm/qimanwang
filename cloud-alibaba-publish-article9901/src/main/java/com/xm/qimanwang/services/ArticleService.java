package com.xm.qimanwang.services;

import com.xm.qimanwang.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    //添加一条文章记录
    public Boolean addArticle(Article article);

    //返回所有记录（测试）
    public List<Article> getArticleAll();

    //根据文章id查询对应的文章
    public Article getArticleById(Long articleid);

    //保存文章信息
    public void saveArticle(Article article);

    //删除对应的文章
    public String deleteArticle(Long articleid);

    //分类获取不同标签的文章信息
    public Map<String, List<Article>> getArticleByTags(String tags);

}

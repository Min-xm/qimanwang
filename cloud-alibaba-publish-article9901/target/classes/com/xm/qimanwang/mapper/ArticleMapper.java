package com.xm.qimanwang.mapper;

import com.xm.qimanwang.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //插入一条记录
    public void inArticle(Article article);

    //查询所有文章信息（测试用）
    public List<Article> getArticleAll();

    //根据文章id查询对应的文章
    public Article getArticleById(Long articleid);

    //保存修改文章
    public void updateArticle(Article article);

    //根据文章id删除对应的文章
    public void deleteArticleById(Long articleid);

    //模糊限制查询文章
    public List<Article> selectArticleByTags(String tags);
}
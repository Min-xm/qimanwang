package com.xm.qimanwang.mapper;

import com.xm.qimanwang.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ArticleMapper {
    public List<Article> getArticleAll();
}
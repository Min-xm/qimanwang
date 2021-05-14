package com.xm.qimanwang.services.impl;

import com.xm.qimanwang.entity.Article;
import com.xm.qimanwang.entity.Like;
import com.xm.qimanwang.mapper.ArticleMapper;
import com.xm.qimanwang.mapper.LikeMapper;
import com.xm.qimanwang.services.LikesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LikesServiceImpl implements LikesService {

    @Resource
    private LikeMapper likeMapper;
    @Resource
    private ArticleMapper articleMapper;


    @Override
    public String like(String phone, Long articleID) {
        Like user = likeMapper.getLikeByUserIdAndArticleId(phone, articleID);
        Article article = articleMapper.getArticleById(articleID);

        Like like = new Like();
        like.setArticleId(articleID);
        like.setPhone(phone);

        Article updateLike = new Article();
        updateLike.setArticleid(article.getArticleid());

        if (user==null){
            like.setJudge(1);
            //文章记录不存在插入一条新纪录默认已点赞
            likeMapper.inLike(like);
            updateLike.setLikes(article.getLikes()+1);
            articleMapper.updateArticle(updateLike);
            System.out.println("用户点赞的文章记录不存在,新建文章点赞");
        }else {
            if (user.getJudge()==1){
                like.setJudge(0);
                likeMapper.updateLike(like);
                updateLike.setLikes(article.getLikes()-1);
                articleMapper.updateArticle(updateLike);
                System.out.println("取消点赞");
                return "进入error";
            }else{
                like.setJudge(1);
                likeMapper.updateLike(like);
                updateLike.setLikes(article.getLikes()+1);
                articleMapper.updateArticle(updateLike);
                System.out.println("点赞成功");
            }
            System.out.println("用户点赞的文章记录存在");
        }
        return "200";
    }


}

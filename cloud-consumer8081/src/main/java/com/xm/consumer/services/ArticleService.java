package com.xm.consumer.services;


import com.xm.qimanwang.entity.Article;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Component
@FeignClient(name = "cloud-provider-publish-article")
public interface ArticleService {

    @GetMapping("/getArticle")
    @ResponseBody
    List<Article> getArticle();

}

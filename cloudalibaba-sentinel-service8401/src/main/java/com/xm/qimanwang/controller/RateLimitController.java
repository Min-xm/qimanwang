package com.xm.qimanwang.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xm.qimanwang.entity.Article;
import com.xm.qimanwang.mapper.ArticleMapper;
import com.xm.qimanwang.myHandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class RateLimitController {
    @Resource
    private ArticleMapper articleMapper;


    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public List<Article> byResource(){
        List<Article> articleAll = articleMapper.getArticleAll();
        System.out.println(articleAll);
        return articleAll;
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2",
            fallback = "handleException")
    public String customerBlockHandler(){
        return "code:200";
    }


    public String handleException(BlockException exception) {
        return "code:444 "+exception.getClass().getCanonicalName();
    }

}

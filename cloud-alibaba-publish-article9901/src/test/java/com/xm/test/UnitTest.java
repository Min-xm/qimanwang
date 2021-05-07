package com.xm.test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xm.qimanwang.PublishArticleMain9901;
import com.xm.qimanwang.entity.Article;
import com.xm.qimanwang.mapper.ArticleMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.List;

@SpringBootTest(classes = PublishArticleMain9901.class)
@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
public class UnitTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();
    }

    @Resource
    private ArticleMapper articleMapper;


    @Test
    public void conflictTime() {
        System.out.println(articleMapper.getArticleAll());
    }


    @Test
    public void checkAddArticleAll() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/getArticle")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("嘟嘟MD独立博客"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Spring Boot干货系列"))
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        //获取返回结果
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        List<Article> article = JSONArray.parseArray(content,Article.class);
        System.out.println(article.size());
        assertThat(article.get(1).getAuthor(),containsString("天小明"));
//        assertThat("",);


    }

}

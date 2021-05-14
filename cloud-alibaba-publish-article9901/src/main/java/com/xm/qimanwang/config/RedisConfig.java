package com.xm.qimanwang.config;

import com.xm.qimanwang.entity.Article;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Article> redisTemplate2(RedisConnectionFactory factory){
        RedisTemplate<String, Article> redisTemplate = new RedisTemplate<>();
        //关联
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Article.class));
        redisTemplate.setHashKeySerializer(new Jackson2JsonRedisSerializer<>(String.class));
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Article.class));
        return redisTemplate;
    }

}

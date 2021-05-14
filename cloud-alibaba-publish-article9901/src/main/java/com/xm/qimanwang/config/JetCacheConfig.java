package com.xm.qimanwang.config;

import java.util.HashMap;
import java.util.Map;

import com.alicp.jetcache.anno.CacheConsts;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.alicp.jetcache.anno.support.GlobalCacheConfig;
import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.alicp.jetcache.embedded.EmbeddedCacheBuilder;
import com.alicp.jetcache.embedded.LinkedHashMapCacheBuilder;
import com.alicp.jetcache.redis.RedisCacheBuilder;
import com.alicp.jetcache.support.FastjsonKeyConvertor;
import com.alicp.jetcache.support.JavaValueDecoder;
import com.alicp.jetcache.support.JavaValueEncoder;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

@Configuration
//激活Cached
@EnableMethodCache(basePackages = "com.xm.qimanwang")
//激活CreateCache
@EnableCreateCacheAnnotation
public class JetCacheConfig {

    @Bean
    public Pool<Jedis> pool(){
        GenericObjectPoolConfig pc = new GenericObjectPoolConfig();
        //设置最新空闲数
        pc.setMinIdle(2);
        //设置最大空闲数
        pc.setMaxIdle(10);
        //设置最大连接数
        pc.setMaxTotal(10);
        //配置Jedis连接池
        return new JedisPool(pc, "120.25.216.140", 6379,3600,"123456");
    }

    @Bean
    public SpringConfigProvider springConfigProvider() {
        return new SpringConfigProvider();
    }

    @Bean
    public GlobalCacheConfig config(SpringConfigProvider configProvider, Pool<Jedis> pool){
        //配置本地缓存
        Map localBuilders = new HashMap();
        EmbeddedCacheBuilder localBuilder = LinkedHashMapCacheBuilder
                .createLinkedHashMapCacheBuilder()
                //每个缓存实例的最大元素的全局配置，仅local类型的缓存需要指定。注意是每个缓存实例的限制，而不是全部，
                // 比如这里指定100，然后用@CreateCache创建了两个缓存实例（并且注解上没有设置localLimit属性），那么每个缓存实例的限制都是100
//                .limit(100)
                //缓存存活时间，如果仍未指定则是无穷大
//                .expireAfterWrite(100, TimeUnit.HOURS)
                //需要jetcache2.2以上,指定多长时间没有访问，就让缓存失效，当前只有本地缓存支持。0表示不使用这个功能。
//                .expireAfterAccess(100,TimeUnit.HOURS)
                .keyConvertor(FastjsonKeyConvertor.INSTANCE);
        localBuilders.put(CacheConsts.DEFAULT_AREA, localBuilder);

        //配置远程缓存
        Map remoteBuilders = new HashMap();
        RedisCacheBuilder remoteCacheBuilder = RedisCacheBuilder.createRedisCacheBuilder()
                //key转换器的全局配置，当前只有一个已经实现的keyConvertor：fastjson。仅当使用@CreateCache且缓存类型为LOCAL时可以指定为none
                // ，此时通过equals方法来识别key。方法缓存必须指定keyConvertor
                .keyConvertor(FastjsonKeyConvertor.INSTANCE)
                //序列化器的全局配置。仅remote类型的缓存需要指定，可选java和kryo
                .valueEncoder(JavaValueEncoder.INSTANCE)
                //序列化器的全局配置。仅remote类型的缓存需要指定，可选java和kryo
                .valueDecoder(JavaValueDecoder.INSTANCE)
                //缓存存活时间，默认无限大
//                .expireAfterWrite(100,TimeUnit.SECONDS)
                .jedisPool(pool);
        remoteBuilders.put(CacheConsts.DEFAULT_AREA, remoteCacheBuilder);

        GlobalCacheConfig globalCacheConfig = new GlobalCacheConfig();
        globalCacheConfig.setConfigProvider(configProvider);
        //创建本地缓存
        globalCacheConfig.setLocalCacheBuilders(localBuilders);
        //创建远程缓存
        globalCacheConfig.setRemoteCacheBuilders(remoteBuilders);
        //统计间隔，0表示不统计
        globalCacheConfig.setStatIntervalMinutes(10);
        //jetcache-anno把cacheName作为远程缓存key前缀，2.4.3以前的版本总是把areaName加在cacheName中，因此areaName也出现在key前缀中。
        // 2.4.4以后可以配置，为了保持远程key兼容默认值为true，但是新项目的话false更合理些。
        globalCacheConfig.setAreaInCacheName(false);
        //@Cached和@CreateCache自动生成name的时候，为了不让name太长，hiddenPackages指定的包名前缀被截掉
//        globalCacheConfig.setHiddenPackages(new String[]{});


        return globalCacheConfig;
    }
}
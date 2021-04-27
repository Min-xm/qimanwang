package com.xm.qimanwang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisHttpSession
public class ProviderLoginMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderLoginMain8801.class,args);
    }
}

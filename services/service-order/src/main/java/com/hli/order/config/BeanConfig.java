package com.hli.order.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author hli
 * @program: cloud2025
 * @Date 2025-07-27 19:34:07
 * @description:
 */
@Configuration
public class BeanConfig {
    @LoadBalanced//注解式负载均衡
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


    /**
     * openfeign重试器
     */
    @Bean
    Retryer feignRetryer() {
        //默认重试间隔100ms,最大间隔1000ms,最多重试5次
        return new Retryer.Default(100, 1000, 5);
    }
}

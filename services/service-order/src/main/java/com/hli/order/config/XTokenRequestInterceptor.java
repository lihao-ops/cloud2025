package com.hli.order.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author hli
 * @program: cloud2025
 * @Date 2025-08-17 18:55:25
 * @description: OpenFeign请求拦截器配置
 */
@Component
public class XTokenRequestInterceptor implements RequestInterceptor {
    /**
     * 拦截请求，添加请求头
     *
     * @param template 请求模板
     */
    @Override
    public void apply(RequestTemplate template) {
        System.out.println("OpenFeign请求拦截器配置启动!");
        template.header("X-Token", UUID.randomUUID().toString());
    }
}

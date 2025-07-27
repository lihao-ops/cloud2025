package com.hli.order.config;

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
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

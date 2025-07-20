package com.hli.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hli
 * @program: cloud2025
 * @Date 2025-06-29 15:27:53
 * @description: 订单服务启动类
 */
@EnableDiscoveryClient//开启服务发现功能
@RestController
@SpringBootApplication
public class OrderMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderMainApplication.class, args);
    }
}

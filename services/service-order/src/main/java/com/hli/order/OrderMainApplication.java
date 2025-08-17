package com.hli.order;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author hli
 * @program: cloud2025
 * @Date 2025-06-29 15:27:53
 * @description: 订单服务启动类
 */
@Slf4j
@EnableDiscoveryClient//开启服务发现功能
@RestController
@EnableFeignClients//开启Feign远程调用功能
@SpringBootApplication
public class OrderMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderMainApplication.class, args);
    }

    //1.项目启动就监听配置文件变化
    //2.发生变化后拿到变化的内容
    //3，发送提醒邮件
    @Bean
    ApplicationRunner applicationRunner(NacosConfigManager nacosConfigManager) {
        return args -> {
            ConfigService configService = nacosConfigManager.getConfigService();
            configService.addListener("service-order.yml", "DEFAULT_GROUP", new Listener() {
                @Override
                public Executor getExecutor() {
                    return Executors.newFixedThreadPool(2);
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("nacosConfig_conversionInfo_listener_info={}", configInfo);
                    log.info("to email!");
                }
            });
            log.info("start_nacosConfig_conversionInfo_listener!");
        };
    }
}

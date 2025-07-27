package com.hli.order;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

/**
 * nacos服务发现测试
 */
@SpringBootTest
public class DiscoveryTest {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    void discoveryClientTest() {
        // 获取所有已注册服务名称
        for (String service : discoveryClient.getServices()) {
            System.out.println(service);
            //获取所有服务实例IP,port,url
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println(instance.getHost());
                System.out.println(instance.getPort());
                System.out.println(instance.getUri());
            }
        }
    }

    @Test
    void nacosServiceDiscoveryTest() throws NacosException {
        // nacosServiceDiscovery也同样可以执行获取,获取所有已注册服务名称
        for (String service : nacosServiceDiscovery.getServices()) {
            System.out.println(service);
            //获取所有服务实例IP,port,url
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println(instance.getHost());
                System.out.println(instance.getPort());
                System.out.println(instance.getUri());
            }
        }
    }
}
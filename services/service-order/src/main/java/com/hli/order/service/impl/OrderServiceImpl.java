package com.hli.order.service.impl;

import com.hli.order.service.OrderService;
import com.hli.order.vo.OrderVO;
import com.hli.product.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author hli
 * @program: cloud2025
 * @Date 2025-07-27 19:09:55
 * @description: 订单实现类
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 创建订单
     *
     * @param productId 商品id
     * @param userId    用户id
     * @return 订单信息
     */
    @Override
    public OrderVO createOrder(Long productId, Long userId) {
        ProductVO productVO = getProductFromRemoteWithLoadBalanceAnnotation(productId);
        OrderVO orderVO = new OrderVO();
        orderVO.setId(2L);
        //(远程查询)总金额(商品单价 * 数量)
        BigDecimal totalAmount = productVO.getPrice().multiply(new BigDecimal(productVO.getNum()));
        orderVO.setTotalAmount(totalAmount);
        orderVO.setUserId(userId);
        orderVO.setNickname("张三");
        orderVO.setAddress("万得大厦");
        //(远程查询)商品列表
        orderVO.setProductList(Arrays.asList(productVO));
        return orderVO;
    }

    /**
     * 1.获取远程商品服务的商品信息
     *
     * @param productId 商品id
     * @return 商品信息
     */
    private ProductVO getProductFromRemote(Long productId) {
        //1.获取到商品服务所在的所有IP+port
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        //2.获取第一个(每次获取第一个商品服务的IP+port),只要商品服务还有一台实例正常运行,调用均能成功
        ServiceInstance serviceInstance = instances.get(0);
        //远程url
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + productId;
        log.info("getProductFromRemote_url={}", url);
        return restTemplate.getForObject(url, ProductVO.class);
    }

    /**
     * 2.进阶：负载均衡远程商品服务的商品信息
     *
     * @param productId 商品id
     * @return 商品信息
     */
    private ProductVO getProductFromRemoteWithLoadBalance(Long productId) {
        //1.负载均衡获取到商品服务IP+port
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        //远程url
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
        log.info("getProductFromRemoteWithLoadBalance_url={}", url);
        return restTemplate.getForObject(url, ProductVO.class);
    }

    /**
     * 3.进阶：注解式负载均衡远程商品服务的商品信息
     *
     * @param productId 商品id
     * @return 商品信息
     */
    private ProductVO getProductFromRemoteWithLoadBalanceAnnotation(Long productId) {
        //以前是类似:http://192.168.6.1:8200/product/{id}
        //远程url(注解式调用只需要将IP+port换成服务名即可),因为在restTemplate会将url中的服务名动态替换成负载均衡的IP+port
        //所以service-product会被动态替换
        String url = "http://service-product/product/" + productId;
        log.info("getProductFromRemoteWithLoadBalance_url={}", url);
        return restTemplate.getForObject(url, ProductVO.class);
    }
}

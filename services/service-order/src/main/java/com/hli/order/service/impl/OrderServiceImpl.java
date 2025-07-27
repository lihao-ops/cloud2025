package com.hli.order.service.impl;

import com.hli.order.service.OrderService;
import com.hli.order.vo.OrderVO;
import com.hli.product.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

    /**
     * 创建订单
     *
     * @param productId 商品id
     * @param userId    用户id
     * @return 订单信息
     */
    @Override
    public OrderVO createOrder(Long productId, Long userId) {
        ProductVO productVO = getProductFromRemote(productId);
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
     * 获取远程商品服务的商品信息
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

    //思考如何在负载均衡调用
}

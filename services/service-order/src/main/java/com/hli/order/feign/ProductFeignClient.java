package com.hli.order.feign;

import com.hli.order.feign.fallback.ProductFeignClientFallback;
import com.hli.product.vo.ProductVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-product",fallback = ProductFeignClientFallback.class)//feign客户端(自动负载均衡)
public interface ProductFeignClient {

    /**
     * mvc注解的两套使用逻辑
     * 1、标注在Controller上，是接受这样的请求
     * 2、标注在FeignClient上，是发送这样的服务
     */
    @GetMapping("/product/{id}")
    ProductVO getProductById(@PathVariable("id") Long id, @RequestParam("userId") Long userId);
}

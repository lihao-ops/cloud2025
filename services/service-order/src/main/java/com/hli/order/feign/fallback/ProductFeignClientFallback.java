package com.hli.order.feign.fallback;

import com.hli.order.feign.ProductFeignClient;
import com.hli.product.vo.ProductVO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author hli
 * @program: cloud2025
 * @Date 2025-08-17 19:10:01
 * @description: 兜底
 */
@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public ProductVO getProductById(Long id, Long userId) {
        System.out.println("兜底回调！");
        ProductVO productVO = new ProductVO();
        productVO.setName("商品不存在");
        productVO.setPrice(new BigDecimal(0));
        productVO.setNum(1);
        productVO.setId(id);
        return productVO;
    }
}

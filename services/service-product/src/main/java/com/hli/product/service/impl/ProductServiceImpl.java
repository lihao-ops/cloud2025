package com.hli.product.service.impl;

import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hli.product.service.ProductService;
import com.hli.product.vo.ProductVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author hli
 * @program: cloud2025
 * @Date 2025-07-27 18:57:27
 * @description: 商品实现类
 */
@Service
public class ProductServiceImpl implements ProductService {
    /**
     * 根据商品id查询商品信息
     *
     * @param productId 商品id
     * @return 商品信息
     */
    @Override
    public ProductVO getProduct(Long productId) {
        ProductVO productVO = new ProductVO();
        productVO.setId(productId);
        productVO.setPrice(new BigDecimal("10.00"));
        productVO.setName("牛奶" + productId);
        productVO.setNum(3);
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return productVO;
    }
}

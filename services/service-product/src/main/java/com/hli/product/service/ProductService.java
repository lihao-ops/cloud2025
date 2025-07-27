package com.hli.product.service;

import com.hli.product.vo.ProductVO;

/**
 * @author hli
 * @program: cloud2025
 * @Date 2025-07-27 18:57:12
 * @description: 商品service
 */
public interface ProductService {
    /**
     * 根据商品id查询商品信息
     *
     * @param productId 商品id
     * @return 商品信息
     */
    ProductVO getProduct(Long productId);
}

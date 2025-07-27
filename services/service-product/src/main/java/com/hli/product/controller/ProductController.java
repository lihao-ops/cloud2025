package com.hli.product.controller;

import com.hli.product.service.ProductService;
import com.hli.product.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hli
 * @program: cloud2025
 * @Date 2025-07-27 18:53:36
 * @description: 商品controller
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("product/{id}")
    public ProductVO getProduct(@PathVariable("id") Long productId) {
        return productService.getProduct(productId);
    }
}

package com.hli.product.controller;

import com.hli.product.service.ProductService;
import com.hli.product.vo.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
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
    public ProductVO getProduct(@PathVariable("id") Long productId, HttpServletRequest request) {
        System.out.println("[" + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] 调用getProduct! + token=" + request.getHeader("X-token"));
        return productService.getProduct(productId);
    }
}

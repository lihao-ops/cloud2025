package com.hli.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hli
 * @program: cloud2025
 * @Date 2025-07-27 18:55:25
 * @description: 商品VO对象
 */
@Data
public class ProductVO {
    private Long id;

    private BigDecimal price;

    private String name;

    private Integer num;
}

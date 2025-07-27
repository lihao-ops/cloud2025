package com.hli.order.vo;

import com.hli.product.vo.ProductVO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author hli
 * @program: cloud2025
 * @Date 2025-07-27 19:06:45
 * @description: 订单vo
 */
@Data
public class OrderVO {
    private Long id;
    //需远程调用结果计算
    private BigDecimal totalAmount;
    private Long userId;
    private String nickname;
    private String address;
    //需远程调用
    private List<ProductVO> productList;
}

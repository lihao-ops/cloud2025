package com.hli.order.controller;

import com.hli.order.service.OrderService;
import com.hli.order.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hli
 * @program: cloud2025
 * @Date 2025-07-27 19:05:40
 * @description: 订单controller
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("create")
    public OrderVO createOrder(@RequestParam("productId") Long productId,
                               @RequestParam("userId") Long userId) {
        return orderService.createOrder(productId, userId);
    }
}

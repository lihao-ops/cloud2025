package com.hli.order.service;

import com.hli.order.vo.OrderVO;

public interface OrderService {
    /**
     * 创建订单
     *
     * @param productId 商品id
     * @param userId    用户id
     * @return 订单对象
     */
    OrderVO createOrder(Long productId, Long userId);
}

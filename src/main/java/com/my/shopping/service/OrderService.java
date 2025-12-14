package com.my.shopping.service;

import com.my.shopping.domain.cart.Cart;
import com.my.shopping.domain.order.Order;
import com.my.shopping.domain.order.dto.OrderCreateDto;
import com.my.shopping.domain.order.dto.OrderUpdateDto;
import com.my.shopping.domain.orderProduct.OrderProduct;

import java.util.List;

public interface OrderService {
    Long insert(OrderCreateDto orderCreateDto);
    Order findById(Long id);
    List<Order> findByMemberId(Long memberId);
    int update(OrderUpdateDto orderUpdateDto);
    int delete(Long id);

    void cancel(Long id);
    List<Order> findByOwnerId(Long ownerId);

    String getNextStatus(String currentStatus);

    String advanceStatus(Long orderId);
    void forceCancel(Long id);

    Long orderFromCart(Cart cart);
    OrderProduct findByOrderProductId(Long orderProductId);
}
package com.my.shopping.service;

import com.my.shopping.domain.order.Order;
import com.my.shopping.domain.order.dto.OrderCreateDto;
import com.my.shopping.domain.order.dto.OrderUpdateDto;

import java.util.List;

public interface OrderService {
    Long insert(OrderCreateDto orderCreateDto);
    Order findById(Long id);
    List<Order> findByMemberId(Long memberId);
    int update(OrderUpdateDto orderUpdateDto);
    int delete(Long id);
}
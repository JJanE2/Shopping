package com.my.shopping.service;

import com.my.shopping.domain.orderProduct.OrderProduct;
import com.my.shopping.domain.orderProduct.dto.OrderProductUpdateDto;

import java.util.List;

public interface OrderProductService {
    void insert(com.my.shopping.domain.orderProduct.dto.OrderProductCreateDto orderProductCreateDto);
    OrderProduct findById(Long id);
    List<OrderProduct> findByOrderId(Long orderId);
    int update(OrderProductUpdateDto orderProductUpdateDto);
    int delete(Long id);
}

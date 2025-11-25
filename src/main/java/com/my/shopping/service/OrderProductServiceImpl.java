package com.my.shopping.service;

import com.my.shopping.domain.orderProduct.OrderProduct;
import com.my.shopping.domain.orderProduct.dto.OrderProductUpdateDto;
import com.my.shopping.mapper.OrderProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {
    private final OrderProductMapper orderProductMapper;

    @Override
    @Transactional
    public void insert(com.my.shopping.domain.orderProduct.dto.OrderProductCreateDto orderProductCreateDto) {
        orderProductMapper.insert(orderProductCreateDto);
    }

    @Override
    public OrderProduct findById(Long id) {
        return orderProductMapper.findById(id);
    }

    @Override
    public List<OrderProduct> findByOrderId(Long orderId) {
        return orderProductMapper.findByOrderId(orderId);
    }

    @Override
    @Transactional
    public int update(OrderProductUpdateDto orderProductUpdateDto) {
        return orderProductMapper.update(orderProductUpdateDto);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return orderProductMapper.delete(id);
    }
}
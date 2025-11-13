package com.my.shopping.service;

import com.my.shopping.domain.order.Order;
import com.my.shopping.domain.order.dto.OrderCreateDto;
import com.my.shopping.domain.order.dto.OrderUpdateDto;
import com.my.shopping.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public Long insert(OrderCreateDto orderCreateDto) {
        orderMapper.insert(orderCreateDto);
        return orderCreateDto.getId();
    }

    @Override
    public Order findById(Long id) {
        return orderMapper.findById(id);
    }

    @Override
    public List<Order> findByMemberId(Long memberId) {
        return orderMapper.findByMemberId(memberId);
    }

    @Override
    @Transactional
    public int update(OrderUpdateDto orderUpdateDto) {
        return orderMapper.update(orderUpdateDto);
    }

    @Override
    public int delete(Long id) {
        return orderMapper.delete(id);
    }
}
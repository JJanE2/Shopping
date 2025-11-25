package com.my.shopping.service;

import com.my.shopping.domain.order.Order;
import com.my.shopping.domain.order.dto.OrderCreateDto;
import com.my.shopping.domain.order.dto.OrderUpdateDto;
import com.my.shopping.domain.orderProduct.OrderProduct;
import com.my.shopping.domain.orderProduct.dto.OrderProductCreateDto;
import com.my.shopping.mapper.OrderMapper;
import com.my.shopping.mapper.OrderProductMapper;
import com.my.shopping.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final OrderProductMapper orderProductMapper;

    @Override
    @Transactional
    public Long insert(OrderCreateDto orderCreateDto) {
        orderMapper.insert(orderCreateDto);
        Long orderId = orderCreateDto.getId();
        List<OrderProductCreateDto> productDtos = orderCreateDto.getProducts();
        insertOrderProducts(orderId, productDtos);
        return orderId;
    }

    private void insertOrderProducts(Long orderId, List<OrderProductCreateDto> productDtos) {
        for (OrderProductCreateDto productDto : productDtos) {
            productDto.setOrderId(orderId);
            orderProductMapper.insert(productDto);
        }
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

    @Override
    @Transactional
    public void cancel(Long id) {
        Order order = orderMapper.findById(id);
        String status = order.getStatus();
        if (status.equals("PAID")) {
            orderMapper.updateStatus(id, "CANCELED");
        }
        throw new IllegalStateException("현재 상태에서 주문을 취소할 수 없습니다.");
    }
}
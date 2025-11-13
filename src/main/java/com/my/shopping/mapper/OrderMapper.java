package com.my.shopping.mapper;

import com.my.shopping.domain.order.Order;
import com.my.shopping.domain.order.dto.OrderCreateDto;
import com.my.shopping.domain.order.dto.OrderUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    void insert(OrderCreateDto orderCreateDto);
    Order findById(Long id);
    List<Order> findByMemberId(@Param("memberId") Long memberId);
    int update(OrderUpdateDto orderUpdateDto);
    int delete(Long id);
}
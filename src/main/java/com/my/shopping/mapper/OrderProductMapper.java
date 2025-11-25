package com.my.shopping.mapper;

import com.my.shopping.domain.orderProduct.OrderProduct;
import com.my.shopping.domain.orderProduct.dto.OrderProductUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderProductMapper {
    void insert(com.my.shopping.domain.orderProduct.dto.OrderProductCreateDto orderProductCreateDto);
    OrderProduct findById(Long id);
    List<OrderProduct> findByOrderId(@Param("orderId") Long orderId);
    int update(OrderProductUpdateDto orderUpdateDto);
    int delete(Long id);
}
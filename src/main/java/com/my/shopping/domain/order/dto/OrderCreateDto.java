package com.my.shopping.domain.order.dto;

import com.my.shopping.domain.orderProduct.dto.OrderProductCreateDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderCreateDto {
    private Long id;
    private Long memberId;
    private Integer totalPrice;
    private List<OrderProductCreateDto> products = new ArrayList<>();
}
package com.my.shopping.domain.order.dto;

import lombok.Data;

@Data
public class OrderRequestDto {
    private Long productId;
    private Integer quantity;
    private Integer totalPrice;
}
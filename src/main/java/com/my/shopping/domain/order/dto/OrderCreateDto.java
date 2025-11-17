package com.my.shopping.domain.order.dto;

import lombok.Data;

@Data
public class OrderCreateDto {
    private Long id;
    private Long memberId;
    private Integer totalPrice;
    private Long productId;
    private Integer quantity;
}
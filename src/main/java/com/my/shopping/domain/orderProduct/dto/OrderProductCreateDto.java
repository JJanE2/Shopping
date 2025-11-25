package com.my.shopping.domain.orderProduct.dto;

import lombok.Data;

@Data
public class OrderProductCreateDto {
    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private Integer price;
    private Integer quantity;
}
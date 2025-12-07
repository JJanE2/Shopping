package com.my.shopping.domain.cart.dto;

import lombok.Data;

@Data
public class CartItemCreateDto {
    private Long id;
    private Long cartId;
    private Long productId;
    private Integer quantity;
}
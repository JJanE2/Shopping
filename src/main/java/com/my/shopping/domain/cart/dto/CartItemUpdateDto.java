package com.my.shopping.domain.cart.dto;

import lombok.Data;

@Data
public class CartItemUpdateDto {
    private Long id;
    private Integer quantity;
}
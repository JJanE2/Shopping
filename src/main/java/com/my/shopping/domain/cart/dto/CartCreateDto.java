package com.my.shopping.domain.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartCreateDto {
    private Long id;
    private Long memberId;
}
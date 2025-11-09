package com.my.shopping.domain.product.dto;

import lombok.Data;

@Data
public class ProductCreateDto {
    private Long id;
    private Long memberId;
    private String name;
    private Integer price;
    private String description;
    private Integer stockQuantity;
}
package com.my.shopping.domain.order.dto;

import lombok.Data;

@Data
public class OrderUpdateDto {
    private Long id;
    private Long memberId;
    private Integer totalPrice;
    private String status;
}

package com.my.shopping.domain.review.dto;

import lombok.Data;

@Data
public class ReviewCreateDto {
    private Long id;
    private Long memberId;
    private Long productId;
    private String content;
    private Integer rating;
    private Long orderProductId;
    private String memberNickname;
}
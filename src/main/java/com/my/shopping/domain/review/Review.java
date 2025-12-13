package com.my.shopping.domain.review;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias(value = "Review")
@Data
@NoArgsConstructor
public class Review {
    private Long id;
    private Long memberId;
    private Long productId;
    private String content;
    private Double rating;
    private Date createdAt;
}
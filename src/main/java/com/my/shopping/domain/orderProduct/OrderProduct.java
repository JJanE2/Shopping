package com.my.shopping.domain.orderProduct;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias(value = "OrderProduct")
@Getter
@Setter
public class OrderProduct {
    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private Integer price;
    private Integer quantity;
    private Long reviewId;
    @Getter(AccessLevel.NONE)
    private String hasWrittenReview;

    // DB에 String 으로 저장하고 사용시에는 boolean 으로 비교해야 하므로 사용
    public boolean isHasWrittenReview() {
        return "Y".equals(hasWrittenReview);
    }
}
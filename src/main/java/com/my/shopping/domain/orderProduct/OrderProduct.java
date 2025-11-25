package com.my.shopping.domain.orderProduct;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias(value = "OrderProduct")
@Data
public class OrderProduct {
    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private Integer price;
    private Integer quantity;
}
package com.my.shopping.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias(value = "CartItem")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long id;
    private Long cartId;
    private Long productId;
    private String productName;
    private Integer price;
    private Integer quantity;
}
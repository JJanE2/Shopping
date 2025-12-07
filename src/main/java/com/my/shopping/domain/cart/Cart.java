package com.my.shopping.domain.cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

@Alias(value = "Cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Long id;
    private Long memberId;
    private List<CartItem> items = new ArrayList<>();
}
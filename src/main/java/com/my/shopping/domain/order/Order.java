package com.my.shopping.domain.order;

import com.my.shopping.domain.orderProduct.OrderProduct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Alias(value = "Order")
@Data
@NoArgsConstructor
public class Order {
    private Long id;
    private Long memberId;
    private Date orderDate;
    private String status;
    private Integer totalPrice;
    private List<OrderProduct> products = new ArrayList<>();
}
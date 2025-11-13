package com.my.shopping.domain.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias(value = "Order")
@Data
@NoArgsConstructor
public class Order {
    private Long id;
    private Long memberId;
    private Date orderDate;
    private String status;
    private Integer totalPrice;
}
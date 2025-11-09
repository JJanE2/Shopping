package com.my.shopping.domain.product;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias(value = "Product")
@Data
public class Product {
    private Long id;
    private Long memberId;
    private String name;
    private Integer price;
    private String description;
    private Integer stockQuantity;
    private Date createdAt;
}
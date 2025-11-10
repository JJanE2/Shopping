package com.my.shopping.domain.product;

import com.my.shopping.domain.product.dto.ProductCreateDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias(value = "Product")
@Data
@NoArgsConstructor
public class Product {
    private Long id;
    private Long memberId;
    private String name;
    private Integer price;
    private String description;
    private Integer stockQuantity;
    private Date createdAt;

    public Product(ProductCreateDto productCreateDto) {
        this.memberId = productCreateDto.getMemberId();
        this.name = productCreateDto.getName();
        this.price = productCreateDto.getPrice();
        this.description = productCreateDto.getDescription();
        this.stockQuantity = productCreateDto.getStockQuantity();
    }
}
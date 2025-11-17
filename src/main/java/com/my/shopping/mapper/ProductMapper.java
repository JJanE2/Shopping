package com.my.shopping.mapper;

import com.my.shopping.domain.product.Product;
import com.my.shopping.domain.product.dto.ProductCreateDto;
import com.my.shopping.domain.product.dto.ProductUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    void insert(ProductCreateDto productCreateDto);
    Product findById(Long id);
    Product findByName(@Param(value = "name") String name);
    int update(ProductUpdateDto productUpdateDto);
    int delete(Long id);
    List<Product> findByMemberId(@Param("memberId") Long memberId);

    int decreaseStock (@Param("productId") Long productId, @Param("quantity") Integer quantity);

    List<Product> findAll();
}
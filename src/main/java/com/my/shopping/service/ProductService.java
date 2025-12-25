package com.my.shopping.service;

import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.product.Product;
import com.my.shopping.domain.product.dto.ProductCreateDto;
import com.my.shopping.domain.product.dto.ProductUpdateDto;

import java.util.List;

public interface ProductService {
    Long insert(ProductCreateDto productCreateDto);
    Product findById(Long id);
    Product findByName(String name);
    int update(ProductUpdateDto productUpdateDto);
    int delete(Long id);
    List<Product> findByMemberId(Long memberId);
    List<Product> findAll();

    void validateOwnerAccess(Member loginMember);

    void validateProductModifyAccess(Member loginMember, Long targetProductId);

    void validateOwnerMyProductsAccess(Member loginMember, Long targetMemberId);
}
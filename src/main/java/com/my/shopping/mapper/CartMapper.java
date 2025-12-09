package com.my.shopping.mapper;

import com.my.shopping.domain.cart.Cart;
import com.my.shopping.domain.cart.dto.CartCreateDto;
import com.my.shopping.domain.cart.dto.CartItemCreateDto;
import com.my.shopping.domain.cart.dto.CartItemDeleteDto;
import com.my.shopping.domain.cart.dto.CartItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartMapper {
    Cart findByMemberId(Long memberId);
    void insertCart(CartCreateDto cartCreateDto);
    void insertCartItem(@Param("createDto") CartItemCreateDto createDto);
    void updateCartItem(@Param("updateDto") CartItemUpdateDto updateDto);
    void deleteCartItem(@Param("deleteDto") CartItemDeleteDto deleteDto);

    void deleteAllCartItems(Long cartId);
}
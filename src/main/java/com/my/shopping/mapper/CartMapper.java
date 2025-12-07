package com.my.shopping.mapper;

import com.my.shopping.domain.cart.Cart;
import com.my.shopping.domain.cart.dto.CartCreateDto;
import com.my.shopping.domain.cart.dto.CartItemCreateDto;
import com.my.shopping.domain.cart.dto.CartItemDeleteDto;
import com.my.shopping.domain.cart.dto.CartItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {
    Cart findByMemberId(Long memberId);
    void insertCart(CartCreateDto cartCreateDto);
    void insertCartItem(CartItemCreateDto createDto);
    void updateCartItem(CartItemUpdateDto updateDto);
    void deleteCartItem(CartItemDeleteDto deleteDto);
}
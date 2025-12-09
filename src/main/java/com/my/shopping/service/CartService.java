package com.my.shopping.service;

import com.my.shopping.domain.cart.Cart;
import com.my.shopping.domain.cart.dto.CartCreateDto;
import com.my.shopping.domain.cart.dto.CartItemCreateDto;
import com.my.shopping.domain.cart.dto.CartItemDeleteDto;
import com.my.shopping.domain.cart.dto.CartItemUpdateDto;

public interface CartService {
    Cart findByMemberId(Long memberId);
    void insertCart(CartCreateDto cartCreateDto);
    void insertCartItem(CartItemCreateDto createDto);
    void updateCartItem(CartItemUpdateDto updateDto);
    void deleteCartItem(CartItemDeleteDto deleteDto);

    void clearCart(Long cartId);
}

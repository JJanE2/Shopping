package com.my.shopping.service;

import com.my.shopping.domain.cart.Cart;
import com.my.shopping.domain.cart.dto.CartCreateDto;
import com.my.shopping.domain.cart.dto.CartItemCreateDto;
import com.my.shopping.domain.cart.dto.CartItemDeleteDto;
import com.my.shopping.domain.cart.dto.CartItemUpdateDto;
import com.my.shopping.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;

    @Override
    public Cart findByMemberId(Long memberId) {
        return cartMapper.findByMemberId(memberId);
    }

    @Override
    @Transactional
    public void insertCart(CartCreateDto cartCreateDto) {
        cartMapper.insertCart(cartCreateDto);
    }

    @Override
    @Transactional
    public void insertCartItem(CartItemCreateDto createDto) {
        cartMapper.insertCartItem(createDto);
    }

    @Override
    @Transactional
    public void updateCartItem(CartItemUpdateDto updateDto) {
        cartMapper.updateCartItem(updateDto);
    }

    @Override
    @Transactional
    public void deleteCartItem(CartItemDeleteDto deleteDto) {
        cartMapper.deleteCartItem(deleteDto);
    }
}
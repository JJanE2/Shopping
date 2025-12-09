package com.my.shopping.controller.api;

import com.my.shopping.domain.cart.dto.CartItemCreateDto;
import com.my.shopping.domain.cart.dto.CartItemDeleteDto;
import com.my.shopping.domain.cart.dto.CartItemUpdateDto;
import com.my.shopping.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartApiController {
    private final CartService cartService;

    @PostMapping("/cart/items")
    public ResponseEntity<Map<String, Object>> addItem(@RequestBody CartItemCreateDto createDto, HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        Long cartId = cartService.findByMemberId(memberId).getId();
        createDto.setCartId(cartId);

        cartService.insertCartItem(createDto);
        return ResponseEntity.ok(Map.of("message", "장바구니에서 물품이 추가되었습니다."));
    }

    @PostMapping("/cart/items/update")
    public Map<String, Object> updateItem(@RequestBody CartItemUpdateDto updateDto) {
        cartService.updateCartItem(updateDto);
        return Map.of("quantity", updateDto.getQuantity());
    }

    @DeleteMapping("/cart/items")
    public Map<String, Object> deleteItem(@RequestBody CartItemDeleteDto deleteDto) {
        cartService.deleteCartItem(deleteDto);
        return Map.of("message", "장바구니에서 물품이 삭제되었습니다.");
    }

    @DeleteMapping("/cart/{id}")
    public Map<String, Object> clearCart(@PathVariable(value = "id") Long cartId) {
        cartService.clearCart(cartId);
        return Map.of("message", "장바구니를 비웠습니다.");
    }
}
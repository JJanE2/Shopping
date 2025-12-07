package com.my.shopping.controller.cart;

import com.my.shopping.domain.cart.Cart;
import com.my.shopping.domain.cart.CartItem;
import com.my.shopping.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart")
    public String getCartPage(HttpSession session, Model model) {
        Long memberId = (Long) session.getAttribute("memberId");
        Cart cart = cartService.findByMemberId(memberId);
        List<CartItem> items = cart.getItems();

        model.addAttribute("items", items);
        return "/cart/cartPage";
    }
}
package com.my.shopping.controller.order;

import com.my.shopping.domain.cart.Cart;
import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.order.Order;
import com.my.shopping.domain.order.dto.OrderCreateDto;
import com.my.shopping.domain.orderProduct.OrderProduct;
import com.my.shopping.service.CartService;
import com.my.shopping.service.OrderProductService;
import com.my.shopping.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderProductService orderProductService;
    private final CartService cartService;

    @PostMapping("/orders/new")
    public String getOrderCreatePage(@ModelAttribute OrderCreateDto orderCreateDto,
                                     HttpSession session, Model model) {
        Long memberId = (Long) session.getAttribute("memberId");

        // 로그인 체크
        if (memberId == null) {
            return "redirect:/login";
        }

        model.addAttribute("orderCreateDto", orderCreateDto);
        model.addAttribute("memberId", memberId);
        return "/orders/orderCreatePage";
    }

    @PostMapping("/orders")
    public String confirmOrder(@ModelAttribute OrderCreateDto orderCreateDto,
                               Model model) {
        try {
            orderService.insert(orderCreateDto);
            return "redirect:/";  // 성공 시 메인으로 이동
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/orders/orderCreatePage"; // 다시 주문 확인 페이지로
        }
    }

    @GetMapping("/orders/{id}")
    public String getOrderDetailsPage(@PathVariable(value = "id") Long id, Model model,
                                      HttpSession session) {
        // 본인 확인 메서드 (주문 상세)
        Member loginMember = (Member) session.getAttribute("member");
        orderService.validateOrderAccess(loginMember, id);

        Order order = orderService.findById(id);
        List<OrderProduct> products = orderProductService.findByOrderId(id);
        model.addAttribute("order", order);
        model.addAttribute("products", products);
        return "/orders/orderDetailsPage";
    }

    @GetMapping("/members/{memberId}/orders")
    public String getMyOrdersPage(@PathVariable(value = "memberId") Long memberId, Model model,
                                  HttpSession session) {
        // 본인 확인 메서드 (내 주문 목록)
        Member loginMember = (Member) session.getAttribute("member");
        orderService.validateMemberMyOrdersAccess(loginMember, memberId);
        List<Order> orders = orderService.findByMemberId(memberId);
        model.addAttribute("orders", orders);
        return "/orders/myOrders";
    }

    @GetMapping("/owner/orders")
    public String getOrderManagementPage(Model model, HttpSession session) {
        Long ownerId = (Long) session.getAttribute("memberId");
        // 권한 확인 메서드 (OWNER 주문 목록)
        orderService.validateOwnerRole(ownerId);
        List<Order> orders = orderService.findByOwnerId(ownerId);
        model.addAttribute("orders", orders);
        return "/orders/ownerOrderManagePage";
    }

    @PostMapping("/orders/cart")
    public String confirmOrderByCart(HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        Cart cart = cartService.findByMemberId(memberId);

        // 주문생성
        Long orderId = orderService.orderFromCart(cart);

        // 주문생성 성공 시 장바구니 비우기
        cartService.clearCart(cart.getId());
        return "redirect:/orders/" + orderId;
    }
}

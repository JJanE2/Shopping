package com.my.shopping.controller.order;

import com.my.shopping.domain.order.Order;
import com.my.shopping.domain.order.dto.OrderCreateDto;
import com.my.shopping.domain.order.dto.OrderRequestDto;
import com.my.shopping.domain.orderProduct.OrderProduct;
import com.my.shopping.service.OrderProductService;
import com.my.shopping.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderProductService orderProductService;

    @PostMapping("/orders/new")
    public String getOrderCreatePage(@ModelAttribute OrderCreateDto orderCreateDto,
                                     @SessionAttribute("memberId") Long memberId, Model model) {
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
    public String getOrderDetailsPage(@PathVariable(value = "id") Long id, Model model) {
        Order order = orderService.findById(id);
        List<OrderProduct> products = orderProductService.findByOrderId(id);
        model.addAttribute("order", order);
        model.addAttribute("products", products);
        return "/orders/orderDetailsPage";
    }

    @GetMapping("/orders/{id}/edit")
    public String getOrderEditPage(@PathVariable(value = "id") Long id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "/orders/orderEditPage";
    }

    @GetMapping("/members/{memberId}/orders")
    public String getMyOrdersPage(@PathVariable(value ="memberId") Long memberId, Model model) {
        List<Order> orders = orderService.findByMemberId(memberId);
        model.addAttribute("orders", orders);
        return "/orders/myOrders";
    }
}

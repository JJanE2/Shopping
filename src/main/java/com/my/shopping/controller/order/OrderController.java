package com.my.shopping.controller.order;

import com.my.shopping.domain.order.Order;
import com.my.shopping.domain.order.dto.OrderRequestDto;
import com.my.shopping.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders/new")
    public String getOrderCreatePage(@ModelAttribute OrderRequestDto orderRequestDto,
                                     @SessionAttribute("memberId") Long memberId, Model model) {
        model.addAttribute("orderRequestDto", orderRequestDto);
        model.addAttribute("memberId", memberId);
        return "/orders/orderCreatePage";
    }

    @GetMapping("/orders/{id}")
    public String getOrderDetailsPage(@PathVariable(value = "id") Long id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "/orders/orderDetailsPage";
    }

    @GetMapping("/orders/{id}/edit")
    public String getOrderEditPage(@PathVariable(value = "id") Long id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "/orders/orderEditPage";
    }
}

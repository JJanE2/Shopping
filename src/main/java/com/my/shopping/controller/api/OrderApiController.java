package com.my.shopping.controller.api;

import com.my.shopping.domain.order.dto.OrderCreateDto;
import com.my.shopping.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderService orderService;

    @PostMapping("/orders/{id}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable(value = "id") Long id) {
        try {
            orderService.cancel(id);
            return ResponseEntity.ok("주문이 취소되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

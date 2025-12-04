package com.my.shopping.controller.api;

import com.my.shopping.domain.order.dto.OrderCreateDto;
import com.my.shopping.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/orders/{id}/status/next")
    public ResponseEntity<Map<String, Object>> advanceStatus(@PathVariable(value = "id") Long id) {
        String nextStatus = orderService.advanceStatus(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "주문상태가 변경되었습니다.");
        response.put("nextStatus", nextStatus);
        return ResponseEntity.ok(response);
    }
}
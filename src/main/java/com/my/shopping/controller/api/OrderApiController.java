package com.my.shopping.controller.api;

import com.my.shopping.domain.order.dto.OrderCreateDto;
import com.my.shopping.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<String> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        Long orderId = orderService.insert(orderCreateDto);
        return ResponseEntity.ok("성공적으로 주문 되었습니다.");
    }
}

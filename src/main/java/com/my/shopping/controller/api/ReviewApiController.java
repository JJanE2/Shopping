package com.my.shopping.controller.api;

import com.my.shopping.domain.review.dto.ReviewCreateDto;
import com.my.shopping.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewApiController {
    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<Map<String, Object>> createReview(@RequestBody ReviewCreateDto reviewCreateDto) {
        Long reviewId = reviewService.insert(reviewCreateDto);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "리뷰가 등록 되었습니다.");
        response.put("reviewId", reviewId);
        return ResponseEntity.ok(response);
    }
}
package com.my.shopping.controller.api;

import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.review.Review;
import com.my.shopping.domain.review.dto.ReviewCreateDto;
import com.my.shopping.domain.review.dto.ReviewUpdateDto;
import com.my.shopping.service.MemberService;
import com.my.shopping.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewApiController {
    private final ReviewService reviewService;
    private final MemberService memberService;

    @PostMapping("/reviews")
    public ResponseEntity<Map<String, Object>> createReview(@RequestBody ReviewCreateDto reviewCreateDto) {
        Member member = memberService.findById(reviewCreateDto.getMemberId());
        reviewCreateDto.setMemberNickname(member.getNickname());
        Long reviewId = reviewService.insert(reviewCreateDto);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "리뷰가 등록 되었습니다.");
        response.put("productId", reviewCreateDto.getProductId());
        return ResponseEntity.ok(response);
    }
}
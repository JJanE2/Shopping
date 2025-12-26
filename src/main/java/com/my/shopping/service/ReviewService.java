package com.my.shopping.service;

import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.review.Review;
import com.my.shopping.domain.review.dto.ReviewCreateDto;
import com.my.shopping.domain.review.dto.ReviewUpdateDto;

import java.util.List;

public interface ReviewService {
    Long insert(ReviewCreateDto reviewCreateDto);
    Review findById(Long id);
    List<Review> findByMemberId(Long memberId);
    List<Review> findByProductId(Long productId);
    int update(ReviewUpdateDto updateDto);
    int delete(Long id);
    void validateReviewMember(Member loginMember, Long targetMemberId);
}
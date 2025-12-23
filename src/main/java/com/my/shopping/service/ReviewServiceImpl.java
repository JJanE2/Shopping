package com.my.shopping.service;

import com.my.shopping.domain.orderProduct.OrderProduct;
import com.my.shopping.domain.review.Review;
import com.my.shopping.domain.review.dto.ReviewCreateDto;
import com.my.shopping.domain.review.dto.ReviewUpdateDto;
import com.my.shopping.exception.ReviewNotFoundException;
import com.my.shopping.mapper.OrderMapper;
import com.my.shopping.mapper.ProductMapper;
import com.my.shopping.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public Long insert(ReviewCreateDto reviewCreateDto) {
        reviewMapper.insert(reviewCreateDto);
        // 리뷰 작성여부 체크 위해 OrderProduct 에 reviewId 추가
        orderMapper.updateReviewId(reviewCreateDto.getOrderProductId(), reviewCreateDto.getId());
        return reviewCreateDto.getId();
    }

    @Override
    public Review findById(Long id) {
        Review review = reviewMapper.findById(id);
        if (review == null) {
            throw new ReviewNotFoundException("존재하지 않는 리뷰입니다.");
        }
        return review;
    }

    @Override
    public List<Review> findByMemberId(Long memberId) {
        return reviewMapper.findByMemberId(memberId);
    }

    @Override
    public List<Review> findByProductId(Long productId) {
        return reviewMapper.findByProductId(productId);
    }

    @Override
    @Transactional
    public int update(ReviewUpdateDto updateDto) {
        return reviewMapper.update(updateDto);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return reviewMapper.delete(id);
    }
}

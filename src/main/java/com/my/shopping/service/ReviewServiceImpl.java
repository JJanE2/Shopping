package com.my.shopping.service;

import com.my.shopping.domain.review.Review;
import com.my.shopping.domain.review.dto.ReviewCreateDto;
import com.my.shopping.domain.review.dto.ReviewUpdateDto;
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

    @Override
    @Transactional
    public Long insert(ReviewCreateDto reviewCreateDto) {
        reviewMapper.insert(reviewCreateDto);
        return reviewCreateDto.getId();
    }

    @Override
    public Review findById(Long id) {
        return reviewMapper.findById(id);
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

package com.my.shopping.mapper;

import com.my.shopping.domain.review.Review;
import com.my.shopping.domain.review.dto.ReviewCreateDto;
import com.my.shopping.domain.review.dto.ReviewUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    void insert(ReviewCreateDto reviewCreateDto);
    Review findById(Long id);
    List<Review> findByMemberId(@Param("memberId") Long memberId);
    List<Review> findByProductId(@Param("productId") Long productId);
    int update(@Param("updateDto") ReviewUpdateDto updateDto);
    int delete(Long id);
}
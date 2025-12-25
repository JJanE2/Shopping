package com.my.shopping.service;

import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.product.Product;
import com.my.shopping.domain.product.dto.ProductCreateDto;
import com.my.shopping.domain.product.dto.ProductUpdateDto;
import com.my.shopping.exception.CustomAccessDeniedException;
import com.my.shopping.exception.LoginRequiredException;
import com.my.shopping.exception.ProductNotFoundException;
import com.my.shopping.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    @Transactional
    public Long insert(ProductCreateDto productCreateDto) {
        productMapper.insert(productCreateDto);
        return productCreateDto.getId();
    }

    @Override
    public Product findById(Long id) {
        Product product = productMapper.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("존재하지 않는 상품입니다.");
        }
        return product;
    }

    @Override
    public Product findByName(String name) {
        return productMapper.findByName(name);
    }

    @Override
    @Transactional
    public int update(ProductUpdateDto productUpdateDto) {
        return productMapper.update(productUpdateDto);
    }

    @Override
    public int delete(Long id) {
        return productMapper.delete(id);
    }

    @Override
    public List<Product> findByMemberId(Long memberId) {
        return productMapper.findByMemberId(memberId);
    }

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public void validateOwnerAccess(Member loginMember) {
        if (loginMember == null) {
            throw new LoginRequiredException();
        }
        if (!loginMember.getRole().equals("OWNER")) {
            throw new CustomAccessDeniedException("회원 유형이 사장님이 아닙니다.");
        }
    }

    @Override
    public void validateProductModifyAccess(Member loginMember, Long targetProductId) {
        if (loginMember == null) {
            throw new LoginRequiredException();
        }
        if (!loginMember.getRole().equals("OWNER")) {
            throw new CustomAccessDeniedException("회원 유형이 사장님이 아닙니다.");
        }
        Product product = findById(targetProductId);
        Long ownerId = product.getMemberId();
        if (!loginMember.getId().equals(ownerId)) {
            throw new CustomAccessDeniedException("본인만 상품을 수정할 수 있습니다.");
        }
    }

    @Override
    public void validateOwnerMyProductsAccess(Member loginMember, Long targetMemberId) {
        if (loginMember == null) {
            throw new LoginRequiredException();
        }
        if (!loginMember.getRole().equals("OWNER")) {
            throw new CustomAccessDeniedException("회원 유형이 사장님이 아닙니다.");
        }
        if (!loginMember.getId().equals(targetMemberId)) {
            throw new CustomAccessDeniedException("본인만 상품을 수정할 수 있습니다.");
        }
    }
}
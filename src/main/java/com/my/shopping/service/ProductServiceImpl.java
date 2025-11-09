package com.my.shopping.service;

import com.my.shopping.domain.product.Product;
import com.my.shopping.domain.product.dto.ProductCreateDto;
import com.my.shopping.domain.product.dto.ProductUpdateDto;
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
    public void insert(ProductCreateDto productCreateDto) {
        productMapper.insert(productCreateDto);
    }

    @Override
    public Product findById(Long id) {
        return productMapper.findById(id);
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
}
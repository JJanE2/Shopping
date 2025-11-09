package com.my.shopping.controller.api;

import com.my.shopping.domain.product.dto.ProductCreateDto;
import com.my.shopping.domain.product.dto.ProductUpdateDto;
import com.my.shopping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductApiController {
    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody ProductCreateDto productCreateDto) {
        productService.insert(productCreateDto);
        return ResponseEntity.ok("성공적으로 상품등록 되었습니다.");
    }

    @PostMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable(value = "id") Long id, @RequestBody ProductUpdateDto productUpdateDto) {
        productService.update(productUpdateDto);
        return ResponseEntity.ok("성공적으로 정보수정 되었습니다.");
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") Long id) {
        productService.delete(id);
        return ResponseEntity.ok("삭제 되었습니다.");
    }
}

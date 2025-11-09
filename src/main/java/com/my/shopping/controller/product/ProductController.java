package com.my.shopping.controller.product;

import com.my.shopping.domain.product.Product;
import com.my.shopping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products/new")
    public String getProductCreatePage(@SessionAttribute("memberId") Long memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "/products/productCreatePage";
    }

    @GetMapping("/products/{id}")
    public String getProductDetailsPage(@PathVariable(value = "id") Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "/products/productDetailsPage";
    }

    @GetMapping("/products/{id}/edit")
    public String getProductEditPage(@PathVariable(value = "id") Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "/products/productEditPage";
    }
}
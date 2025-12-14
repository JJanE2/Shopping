package com.my.shopping.controller.product;

import com.my.shopping.domain.product.Product;
import com.my.shopping.domain.review.Review;
import com.my.shopping.service.ProductService;
import com.my.shopping.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ReviewService reviewService;

    @GetMapping("/products/new")
    public String getProductCreatePage(@SessionAttribute("memberId") Long memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "/products/productCreatePage";
    }

    @GetMapping("/products/{id}")
    public String getProductDetailsPage(@PathVariable(value = "id") Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        List<Review> reviews = reviewService.findByProductId(id);
        model.addAttribute("reviews", reviews);
        return "/products/productDetailsPage";
    }

    @GetMapping("/products/{id}/edit")
    public String getProductEditPage(@PathVariable(value = "id") Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "/products/productEditPage";
    }

    @GetMapping("/members/{memberId}/products")
    public String getMyProductsPage(@PathVariable(value ="memberId") Long memberId, Model model) {
        List<Product> products = productService.findByMemberId(memberId);
        model.addAttribute("products", products);
        return "/products/myProducts";
    }
}
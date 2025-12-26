package com.my.shopping.controller.review;

import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.orderProduct.OrderProduct;
import com.my.shopping.domain.review.Review;
import com.my.shopping.exception.LoginRequiredException;
import com.my.shopping.service.OrderService;
import com.my.shopping.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final OrderService orderService;

    @GetMapping("/reviews/new")
    public String getReviewCreatePage(@RequestParam("orderProductId") Long orderProductId, Model model,
                                      HttpSession session){
        Long memberId = (Long) session.getAttribute("memberId");
        if (memberId == null) {
            throw new LoginRequiredException();
        }
        OrderProduct orderProduct = orderService.findByOrderProductId(orderProductId);
        Long productId = orderProduct.getProductId();

        model.addAttribute("productId", productId);
        model.addAttribute("orderProductId", orderProductId);
        model.addAttribute("memberId", memberId);
        return "/reviews/reviewCreatePage";
    }

    @GetMapping("/reviews/{id}/edit")
    public String getReviewEditPage(@PathVariable(value = "id") Long id, Model model,
                                    HttpSession session) {
        Review review = reviewService.findById(id);
        // 본인 확인 메서드 (리뷰 수정)
        Member loginMember = (Member) session.getAttribute("member");
        reviewService.validateReviewMember(loginMember, review.getMemberId());
        model.addAttribute("review", review);
        return "/reviews/reviewEditPage";
    }
}
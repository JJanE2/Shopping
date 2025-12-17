package com.my.shopping.controller.member;

import com.my.shopping.domain.member.Member;
import com.my.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Value("${kakao.client-id}")
    private String kakaoClientId;

    @Value("${kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @GetMapping("/members/new")
    public String getMemberCreatePage() {
        return "/members/memberCreatePage";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("kakaoClientId", kakaoClientId);
        model.addAttribute("kakaoRedirectUri", kakaoRedirectUri);
        return "/members/loginPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/members/{id}")
    public String getMemberDetailsPage(@PathVariable(value = "id") Long id, Model model) {
        Member member = memberService.findById(id);
        model.addAttribute("member", member);
        return "/members/memberDetailsPage";
    }

    @GetMapping("/members/{id}/edit")
    public String getMemberEditPage(@PathVariable(value = "id") Long id, Model model) {
        Member member = memberService.findById(id);
        model.addAttribute("member", member);
        return "/members/memberEditPage";
    }
}

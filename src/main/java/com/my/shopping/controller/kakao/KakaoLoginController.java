package com.my.shopping.controller.kakao;

import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.member.dto.KakaoUserInfo;
import com.my.shopping.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class KakaoLoginController {
    private final KakaoService kakaoService;

    @GetMapping("/kakao/callback")
    public String kakaoCallback(@RequestParam String code,
                                HttpSession session) {
        String accessToken = kakaoService.getAccessToken(code);
        KakaoUserInfo userInfo = kakaoService.getUserInfo(accessToken);

        // 로그인 또는 회원가입 및 로그인 처리
        Member member = kakaoService.loginOrSignUp(userInfo);

        // 세션 로그인 처리
        session.setAttribute("member", member);
        session.setAttribute("memberId", member.getId());
        // 회원탈퇴 시 unlink 위해 accessToken 저장
        session.setAttribute("KAKAO_ACCESS_TOKEN", accessToken);

        return "redirect:/";
    }
}
package com.my.shopping.service;

import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.member.dto.KakaoUserInfo;
import com.my.shopping.service.oauth.KakaoOAuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KakaoService {
    private final KakaoOAuthClient kakaoOAuthClient;
    private final MemberService memberService;

    public String getAccessToken(String code) {
        return kakaoOAuthClient.getAccessToken(code);
    }

    public KakaoUserInfo getUserInfo(String accessToken) {
        return kakaoOAuthClient.getUserInfo(accessToken);
    }

    public Member loginOrSignUp(KakaoUserInfo kakaoUser) {
        // kakao 회원가입 한적 있으면 바로 로그인
        Member member = memberService.findByKakaoId(kakaoUser.getId());
        if (member != null) {
            return member;
        }
        // kakao 회원가입 한적 없으면 회원가입 후 로그인
        return memberService.joinByKakao(kakaoUser);
    }

    @Transactional
    public void unlink(String accessToken) {
    }
}
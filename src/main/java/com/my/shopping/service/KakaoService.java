package com.my.shopping.service;

import com.my.shopping.domain.member.Member;
import com.my.shopping.domain.member.dto.KakaoUserInfo;
import com.my.shopping.service.oauth.KakaoOAuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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
        // 요청 URL
        String url = "https://kapi.kakao.com/v1/user/unlink";

        // RestTemplate 생성
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken); // Bearer {accessToken}
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 요청 엔티티 생성
        HttpEntity<String> request = new HttpEntity<>(headers);

        // API 호출
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                String.class
        );
        // 응답 상태 확인
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("카카오 unlink 성공");
        } else {
            System.out.println("카카오 unlink 실패: " + response.getStatusCode());
            System.out.println("응답 바디: " + response.getBody());
        }
    }
}
package com.my.shopping.service.oauth;

import com.my.shopping.domain.member.dto.KakaoTokenResponse;
import com.my.shopping.domain.member.dto.KakaoUserInfo;
import com.my.shopping.domain.member.dto.KakaoUserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoOAuthClient {
    @Value("${kakao.client-id}")
    private String kakaoClientId;

    @Value("${kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Value("${kakao.client-secret}")
    private String kakaoClientSecret;

    public String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                MediaType.APPLICATION_FORM_URLENCODED
        );

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId);
        params.add("redirect_uri", kakaoRedirectUri);
        params.add("client_secret", kakaoClientSecret);
        params.add("code", code);

        HttpEntity<LinkedMultiValueMap<String, String>> request =
                new HttpEntity<>(params, headers);

        ResponseEntity<KakaoTokenResponse> response =
                restTemplate.postForEntity(
                        "https://kauth.kakao.com/oauth/token",
                        request,
                        KakaoTokenResponse.class
                );


        return response.getBody().getAccessToken();
    }

    public KakaoUserInfo getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<KakaoUserResponse> response =
                restTemplate.exchange(
                        "https://kapi.kakao.com/v2/user/me",
                        HttpMethod.POST,
                        request,
                        KakaoUserResponse.class
                );

        KakaoUserResponse body = response.getBody();

        return new KakaoUserInfo(
                String.valueOf(body.getId()),
                body.getKakaoAccount().getEmail(),
                body.getKakaoAccount().getProfile().getNickname()
        );
    }
}
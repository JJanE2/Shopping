package com.my.shopping.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserResponse {
    private Long id;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;
    @Getter
    public static class KakaoAccount {
        private String email;
        private Profile profile;
    }
    @Getter
    public static class Profile {
        private String nickname;
    }
}
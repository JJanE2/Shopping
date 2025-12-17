package com.my.shopping.domain.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoUserInfo {
    private String id;
    private String email;
    private String nickname;
}
package com.my.shopping.domain.member.dto;

import lombok.Data;

@Data
public class MemberCreateDto {
    private Long id;
    private String loginId;
    private String nickname;
    private String password;
}
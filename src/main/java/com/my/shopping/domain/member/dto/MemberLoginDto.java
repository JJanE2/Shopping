package com.my.shopping.domain.member.dto;

import lombok.Data;

@Data
public class MemberLoginDto {
    private String loginId;
    private String password;
}
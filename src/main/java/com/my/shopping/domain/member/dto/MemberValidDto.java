package com.my.shopping.domain.member.dto;

import lombok.Data;

@Data
public class MemberValidDto {
    private String loginId;
    private String nickname;
    private String password;
}

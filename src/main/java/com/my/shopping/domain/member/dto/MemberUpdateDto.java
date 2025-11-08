package com.my.shopping.domain.member.dto;

import lombok.Data;

@Data
public class MemberUpdateDto {
    private Long id;
    private String nickname;
    private String password;
}

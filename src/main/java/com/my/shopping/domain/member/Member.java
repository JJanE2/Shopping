package com.my.shopping.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias(value = "Member")
@Data
@AllArgsConstructor
public class Member {
    private Long id;
    private String nickname;
    private String loginId;
    private String password;
    private String role;
}